# week5

## SWEA

### 미생물 격리
- 접근 방법
    - time complexity : O(N^2)*K <1,000,000,000, time limit : 5 sec
    - 어차피 군집의 교체는 1대1로 이루어지므로 큰 군집부터 이동 시키면서 군집의 크기를 더해가는 방법
    - 미생물 군집이 하나 되는 경우
        - 큰 군집부터 이동시키는 것이 구현하기 편함
            - 작은 군집들이 뭉쳐서 큰 군집의 방향을 유지하지 못하게 하기 때문(Comparable 구현)
        - 삽입/삭제가 계속해서 일어나므로 그에 유리한 자료구조를 선택하는 것이 좋음