# week10

- 운영체제 강의 ->A형 대비로 취소
- 라이브 코딩

## 추후 일정
```
공지 : 추후 오프라인 일정으로 인해 요일은 동결/ 9시 30분으로 시간 조정
17일 - A형 대비 라이브 코딩(SWEA), MLFQ, Multiprocess scheduling
20일 - OS: 13. Address Spaces, 14.Memory API
```

## BOJ

### 2573: 빙산
- 접근 방법
    - 각 빙산마다 4방 탐색을 시도해서 숫자 갱신
    - 갱신한 정보를 맵에 등록
    - 맵을 기반으로 격자 탐색 진행
    - 격자 탐색을 한 결과 방문한 빙산의 갯수가 전체 빙산의 갯수보다 작은 경우, 분리되었다는 의미이므로 정답 출력
    - 같은 경우, 위 과정을 반복

### 1339: 단어 수학
- 접근 방법
    - 1. 완탐
        - 운이 좀 필요하다고 생각
        - 시간 복잡도 계산
            - 각 알파벳(max = 10EA)에 숫자 설정 = 10!
            - 각 수마다 알파벳->숫자 변경 = 8
            - 모든 단어마다 진행 = N
        - 2억< 10! * 8 * 10 <3억
        - 안정적으로 2초안에 들어올 수 있을지 의문 생김
    - 2. 그리디
        - 가장 높은 자리에 존재하는 알파벳에 가장 높은 수를 주는 것이 클 것.
        - 자리가 같은 경우 단어의 빈도수를 체크해서 숫자를 지정해주면 된다.
        - 시간 복잡도 계산
            - 각 알파벳의 빈도 파악 = O(10*8)
            - 자릿수/빈도에 따라 정렬 = O(10)
            - 모든 단어마다 알파벳->숫자 변경 = 8*10
        - O(10*8) + O(N) + O(N*8)

## Reference

- boj : [문제집](https://www.acmicpc.net/group/workbook/view/15281/49835)

- lecture : [운영체제](http://www.kocw.net/home/search/kemView.do?kemId=1046323), [OSTEP](https://pages.cs.wisc.edu/~remzi/OSTEP/)
[OSTEP 번역](https://github.com/remzi-arpacidusseau/ostep-translations/blob/master/korean/README.md)
