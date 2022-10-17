# week11

- 라이브 코딩
- 운영체제 강의

## 추후 일정
```
공지 : 추후 오프라인 일정으로 인해 요일은 동결/ 9시 30분으로 시간 조정
17일 - A형 대비 라이브 코딩(SWEA), MLFQ, Multiprocess scheduling
20일 - OS: 13. Address Spaces, 14.Memory API
```

## BOJ

## Operating System
MLFQ
- Existing scheduling policies
    - FIFO, SJF, STCF : good for turnaround time, terrible for response time
    - RR : vice versa
- How to optimize the turnaround time while minimizing response time?
    - MLFQ ( Multi-Level Feedback Queue)
    - Approach : Learn from the past to predict the future
- Consist of multiple queues
- Each queue is assign a diff priority level
- A job that is ready to run is on a single queue
- A job with higher priority is chosen to run next(RR among jobs in the same queue)
    - How to assign a priority to each process?
        - e.g
            - Rule 1: If Priority(A) > Priority(B), A runs (B doesn’t).
            - Rule 2: If Priority(A) = Priority(B), A & B run in round-robin fashion using the time slice (quantum length) of the given queue.
            - Rule 3: When a job enters the system, it is placed at the highest priority (the topmost queue).
            - Rule 4: Once a job uses up its time allotment at a given level (regardless of how many times it has given up the CPU), its priority is reduced (i.e., it moves down one queue).
            - Rule 5: After some time period S, move all the jobs in the system to the topmost queue.
    - Not fixed, change the priority of a job based on its observed behavior(feedback)
- Problem with our current MLFQ
    - Issues
        - Starvation
        - User can trick the scheduler(game the scheduler)
        - A program may change its behavior

Proportional Share(fair share)
- Concept : instead of turnaround time or response time, it tries to guarantee that each job obtain a certain percentage of CPU time(especially important for Cloud System)
- Scheduling algorithms : Lottery, Stride

Lottery Scheduling
- Schedule a job who wins the lottery
- A job that has more tickets has more chance to win
    - ticket : represent the share of resource
    - Two jobs, A has 75% tickets while B has 25% tickets -> win probability with 75%, 25% -> 75% of CPU is excepted to be used by A

- Ticket currency
    - Allow users to allocate tickets among their own jobs with correct global value
    - Example
        - Two users, A:100 tickets, B: 100 tickets
        - A has two jobs. A gives them each 500 tickets
        - B has only one jobs. B give it 10 tickets
        - How many tickets are given into three jobs with a global viewpoint?
    - Ticket Transfer
        - A job temporarily hands off its tickets to another job
        - especially useful in a client/server env
- Benefit of Lottery scheduling
    - simplicity
- Not deterministic(rely on random number generator)

Stride Scheduling
- A deterministic fair-share scheduler
    - Key concept : Stride -> Inverse in proportion to the # of tickets
    - How to schedule
        - Schedule a job who has the smallest pass value
        - Increment the pass value by its stride

CPU cache
- small, fast memory that generally holds copies of popular data(based on temporal and spatial locality)
    - temporal locality : when a data is accessed, it is likely to be accessed again in the near future(e.g. stack, for loop …)
    - Spatial locality : when a data is accessed, it is likely to access data near as well(e.g. array, sequential execution, …)
- Benefit
    - Cache hit : make a program run fast by reducing access to the relatively slow main memory
    - Delayed write : Modified data are kept in cache, not writing immediately into memory so that is possibly merges consecutive writes into a single memory access

Issues on Multiprocessor
- Cache affinity
    - when process runs, it is often advantageous to run it on the same CPU where the process ran previously
    - Since the CPU might build up a state in the cache(and TLB) for the process


## Reference

- boj : [문제집](), [로봇청소기](https://www.acmicpc.net/problem/14503)

- lecture : [운영체제](http://www.kocw.net/home/search/kemView.do?kemId=1046323), [OSTEP](https://pages.cs.wisc.edu/~remzi/OSTEP/)
[OSTEP 번역](https://github.com/remzi-arpacidusseau/ostep-translations/blob/master/korean/README.md)
