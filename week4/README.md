# week3
- implements, simulation

## 추후 일정
```
5일 - 알고리즘 라이브 코딩(SWEA)
8일 - pass
12일 - 보류 
15일 - cs
```
스터디 뒤풀이 한강은 은제감?
## BOJ

### 18428: 감시 피하기 
- 접근 방법
    - 장애물(3개)을 놓을 자리를 조합을 통해 구현
    - 감시를 한번이라도 피하지 못하면 끝이므로 flag 를 이용하여 바로 loop 를 탈출하는 식으로 구현
        - or 연산을 활용하면 편하다.

### 7573: 고기잡이
- 접근 방법
    - 물고기를 가장 끝에 걸치게 되면 많은 제약이 생겨 정답을 맞추지 못함
    - 그물의 길이를 먼저 정한 후, 그 그물을 물고기가 오른쪽 아래부터 왼쪽 위까지 위치하도록 그물을 밀어서 물고기 갯수를 카운팅
    - int type의 맵을 만들면 메모리가 터지므로 주의

### 11559: Puyo Puyo
- 접근 방법
    - 같은 타입의 블록을 BFS로 탐색한 후에 블록의 갯수가 4개 이상이면 터뜨림
    - 터뜨린 후, 아래부터 탐색하여 위에 블럭이 있는 경우 계속 위치를 바꿔주며 블럭을 아래로 내림
    - 열이 6개라는 명확한 기준이 있으므로 ArrayList 의 remove 를 활용하면 터뜨리고 난 후의 위에 존재하는 블럭을 재배치할 필요가 없음
        - remove 의 삭제연산 후에 resizing cost 를 생각해서 LinkedList 를 쓰는것이 가장 현명하다고 판단됨

### 20061: 모노미노도미노2
- 접근 방법
    - 결국 블럭을 떨어뜨리고 블럭이 차면 터뜨리는 똑같은 과정을 반복 하는 것이므로, 좌표를 잘 변환하여 같은 함수를 재사용
    - 각 row 마다의 블럭 갯수를 counting 하여 특정 갯수 이상이 되었을 경우 삭제
        - 비트마스킹으로도 가능하다고 함

### 16985: Maaaaaaaaaze
- 접근 방법
    - 각 층의 순열(O(5!)) x 회전수 및 회전(O(4^5)) x BFS(O(5^3))
    - 시작점은 고려하지 않아도 됨
        - 어차피 회전에 출발점이 고려가 되어 있음..


### 9370: 미확인 도착지
- 접근 방법
    - 푼사람 도와조라!!!!!!!!!!!

## Programmers

### 후보키(Candidate Key)

## Operating System

- 목표 : 한 달안에 다 부셔버리기
- 이번 주
    - 강의
        - process 의 개념, 상태, 상태도
        - context switching, 스케줄링을 위한 queue..(scheduler)
        - 동기/비동기식 입출력, thread
        - single/multi processing, threads
    - 자료(OSTEP)
        - part 5 : process API

## Reference

- Boj : https://www.acmicpc.net/problem/18428, https://www.acmicpc.net/problem/7573, https://www.acmicpc.net/problem/11559, https://www.acmicpc.net/problem/20061, https://www.acmicpc.net/problem/16985, https://www.acmicpc.net/problem/9370

- programmers : https://school.programmers.co.kr/learn/courses/30/lessons/42890

- lecture : [운영체제](http://www.kocw.net/home/search/kemView.do?kemId=1046323), [OSTEP](https://pages.cs.wisc.edu/~remzi/OSTEP/)

- 