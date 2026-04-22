import sys
import re
import os
import json
import subprocess
import time
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def find_repo_root():
    current_dir = os.path.dirname(os.path.abspath(__file__))
    while current_dir != os.path.dirname(current_dir):
        if "src" in os.listdir(current_dir): return current_dir
        current_dir = os.path.dirname(current_dir)
    return os.path.dirname(os.path.abspath(__file__))

PROBLEM_DIR = find_repo_root()
CACHE_DIR = os.path.join(find_repo_root(), "temp", ".leetcode_cache")


def parse_input_with_types(input_str, param_types):
    """
    메서드 시그니처의 타입 정보를 바탕으로 입력값을 코틀린 코드로 변환
    """
    # 1. 입력 문자열에서 값들만 분리 (쉼표 기준, 괄호 쌍 고려)
    input_content = re.sub(r'[a-zA-Z0-9]+\s*=\s*', '', input_str)
    raw_values = []
    bracket_level = 0
    current = ""
    for char in input_content:
        if char == '[': bracket_level += 1
        elif char == ']': bracket_level -= 1
        if char == ',' and bracket_level == 0:
            raw_values.append(current.strip()); current = ""
        else: current += char
    if current: raw_values.append(current.strip())

    kt_args = []
    for i, val in enumerate(raw_values):
        target_type = param_types[i] if i < len(param_types) else "Any"

        if val.startswith('['):
            content = val[1:-1].strip()

            # 빈 배열/리스트 처리
            if not content:
                if "Array<IntArray>" in target_type: kt_args.append("emptyArray<IntArray>()")
                elif "IntArray" in target_type: kt_args.append("intArrayOf()")
                elif "Array<String>" in target_type: kt_args.append("emptyArray<String>()")
                elif "List" in target_type: kt_args.append("emptyList()")
                else: kt_args.append("emptyArray()")
                continue

            # 2차원 정수 배열 처리 (Array<IntArray>)
            if "Array<IntArray>" in target_type:
                inner = re.findall(r'\[([^\[\]]*)\]', val)
                items = [f"intArrayOf({x})" if x.strip() else "intArrayOf()" for x in inner]
                kt_args.append(f"arrayOf({', '.join(items)})")
            # 문자열 배열/리스트 처리 (Array<String>, List<String>)
            elif "String" in target_type:
                if "List" in target_type:
                    kt_args.append(f"listOf({content})")
                else:
                    kt_args.append(f"arrayOf({content})")
            # 정수 배열 처리 (IntArray)
            elif "IntArray" in target_type:
                kt_args.append(f"intArrayOf({content})")
            # 기본 객체 배열 (Array<Int> 등)
            else:
                kt_args.append(f"arrayOf({content})")
        else:
            kt_args.append(val)

    return ", ".join(kt_args)

def generate_wrapped_kotlin(original_kt, input_str):
    with open(original_kt, 'r', encoding='utf-8') as f:
        content = f.read()

    # 클래스 및 메서드 정보 추출
    class_name = re.search(r'class\s+([a-zA-Z0-9_]+)', content).group(1)
    method_match = re.search(r'fun\s+([a-zA-Z0-9_]+)\s*\((.*?)\)(?:\s*:\s*([a-zA-Z0-9_<>\s]+))?', content, re.DOTALL)
    method_name = method_match.group(1)
    params_raw = method_match.group(2)
    return_type = method_match.group(3).strip() if method_match.group(3) else "Unit"

    # 파라미터 타입 추출 및 인자 파싱
    param_types = [p.split(':')[-1].strip() for p in params_raw.split(',') if ':' in p]
    parsed_args = parse_input_with_types(input_str, param_types)

    # 반환 타입에 따른 '정확한' 출력 구문 생성 (VerifyError 및 모호성 해결)
    # 백엔드 개발자로서 타입 안전성을 보장하기 위해 구체적인 포맷 지정
    if return_type == "IntArray":
        print_stmt = f"println(instance.{method_name}({parsed_args}).joinToString(\",\", \"[\", \"]\"))"
    elif "List<String>" in return_type or "Array<String>" in return_type:
        # 문자열 리스트/배열은 각 요소에 따옴표를 붙여 출력 (리트코드 포맷)
        print_stmt = f"println(instance.{method_name}({parsed_args}).joinToString(\",\", \"[\", \"]\") {{ \"\\\"$it\\\"\" }})"
    elif "List" in return_type:
        print_stmt = f"println(instance.{method_name}({parsed_args}).joinToString(\",\", \"[\", \"]\"))"
    elif "Array<IntArray>" in return_type:
        print_stmt = f"println(instance.{method_name}({parsed_args}).contentDeepToString().replace(\" \", \"\"))"
    elif "Array<" in return_type:
        print_stmt = f"println(instance.{method_name}({parsed_args}).contentToString().replace(\" \", \"\"))"
    elif return_type == "Unit":
        print_stmt = f"instance.{method_name}({parsed_args}); println(\"Done\")"
    else:
        # Int, String 등 기본 타입 출력
        print_stmt = f"println(instance.{method_name}({parsed_args}))"

    wrapper = f"""
{content}

fun main() {{
    val instance = {class_name}()
    println("[RESULT_START]")
    try {{
        {print_stmt}
    }} catch (e: Exception) {{
        println("RUNTIME_ERROR: ${{e.message}}")
    }}
    println("[RESULT_END]")
}}
"""
    return wrapper


def run_kotlin_test(kt_file, test_cases):
    temp_kt = "TempSolution.kt"
    jar_name = "temp_leetcode.jar"

    for idx, case in enumerate(test_cases, 1):
        try:
            with open(temp_kt, "w", encoding="utf-8") as f:
                f.write(generate_wrapped_kotlin(kt_file, case['input']))

            # 컴파일
            compile_res = subprocess.run(["kotlinc", temp_kt, "-include-runtime", "-d", jar_name],
                                         capture_output=True, text=True)
            if compile_res.returncode != 0:
                print(f"--- CASE {idx} ---\n❌ 컴파일 에러:\n{compile_res.stderr}\n")
                continue

            # 실행
            res = subprocess.run(["java", "-jar", jar_name], capture_output=True, text=True)

            actual = ""
            if "[RESULT_START]" in res.stdout:
                actual = res.stdout.split("[RESULT_START]")[1].split("[RESULT_END]")[0].strip()

            print(f"--- CASE {idx} ---")
            print(f"Input: {case['input']}")
            print(f"Output: {actual}")
            print(f"Expected: {case['output']}")

            if actual == case['output'].replace(" ", ""):
                print("✅ PASSED")
            else:
                print("❌ FAILED")
            if res.stderr: print(f"⚠️ STDERR: {res.stderr}")
            print("-" * 30)

        except Exception as e:
            print(f"❌ 예외 발생: {e}")

    if os.path.exists(temp_kt): os.remove(temp_kt)
    if os.path.exists(jar_name): os.remove(jar_name)

def fetch_leetcode_cases(problem_slug):
    url = f"https://leetcode.com/problems/{problem_slug}/"
    print(f"🌍 리트코드 서버 접속 중... ({url})")
    options = Options()
    options.add_argument("--headless")
    options.add_argument("--disable-blink-features=AutomationControlled")
    options.add_argument("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36")
    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    test_cases = []
    try:
        driver.get(url)
        wait = WebDriverWait(driver, 15)
        wait.until(EC.presence_of_element_located((By.TAG_NAME, "pre")))
        soup = BeautifulSoup(driver.page_source, 'html.parser')
        for ex in soup.find_all('pre'):
            text = ex.get_text()
            if "Input:" in text and "Output:" in text:
                try:
                    parts = text.split("Output:")
                    test_cases.append({
                        "input": parts[0].replace("Input:", "").strip(),
                        "output": parts[1].split("Explanation:")[0].strip()
                    })
                except: continue
        return test_cases
    finally: driver.quit()

def main():
    if len(sys.argv) < 2: return
    problem_name = sys.argv[1]
    problem_slug = re.sub(r'(?<!^)(?=[A-Z])', '-', problem_name).lower()

    target_file = None
    for root, _, files in os.walk(PROBLEM_DIR):
        if f"{problem_name}.kt" in files:
            target_file = os.path.join(root, f"{problem_name}.kt")
            break
    if not target_file: return

    os.makedirs(CACHE_DIR, exist_ok=True)
    cache_path = os.path.join(CACHE_DIR, f"{problem_slug}.json")
    if os.path.exists(cache_path):
        with open(cache_path, "r") as f: test_cases = json.load(f)
    else:
        test_cases = fetch_leetcode_cases(problem_slug)
        if test_cases:
            with open(cache_path, "w") as f: json.dump(test_cases, f)

    if test_cases: run_kotlin_test(target_file, test_cases)

if __name__ == "__main__":
    main()