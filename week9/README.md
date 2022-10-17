# week9

- 운영체제 강의
- 코드 리뷰

## 추후 일정
```
10일 - A형 대비 라이브 코딩(SWEA), 코드 리뷰
13일 - OS: CPU scheduling, Multi-level Feedback
```

## BOJ

### 20057: 마법사 상어와 토네이도 
- 접근 방법
    - 가운데를 중점으로 달팽이 배열 구현
        - dx, dy 를 활용하여 구현
        - 1 step(2번) 이 지나고 나면 1회씩 이동량이 증가하도록 구현
    - 문제에서 요구하는 대로 모래를 분산
        - 이동할 좌표를 [x,y,ratio] 와 같이 만들고 이동하면서 그 칸의 비율을 적용 시키면 편함
        - 이동한 좌표의 한 칸 앞으로 남은 모래가 이동함을 주의(소수점 모래는 이동하지 않는 것으로 판단)

## Operating System
Scheduling
- multiple actors want to use resources at a time(limited resources)
Process Scheduling
- actor : process, resource : processor
- Select a process who run on a processor(or processors)

Add Process status diagram

Workload
- the amount of work to be done
- How much resources are required by a set of processes with the consideration of their characteristics(in CS)
Metric
- something that we use to measure( e.g performance, reliablilty)
Metrics for scheduling
- Turnaround time
    - T_turnaround = T_completion - T_arrival
- Response time
    - T_response = T_firstrun - T_arrival
- Fairness
- Throughput
    - E.g) Number of completed processes / 1 hour
- Deadline

A simple assumption about process(for educate)
1. Each job runs for the same amount of time
2. All jobs arrives at the same time
3. Once started, each job runs to completion
4. All jobs only use the CPU(no I/O)
5. The run-time of each job is known in advance

FIFO(First in First out)
- Schedule a process that arrives first( a.k.a FCFS)
- pros
    - Simple
    - Easy to implement
- cons
    - Long waiting time(convoy effect)

SJF(Shortest Job First)
- give a higher priority to the shortest job
- Cons
    - Convoy effect

STCF(Shortest Time to Completion First)
- Similar to SJF, but preemptive version(a.k.a Shortest Remaining-Time next)
- Non preemptive scheduling
    - Run a job to completion
- Preemptive scheduling
    - Can stop a job (even though it is not completed yet) to run another job
    - All modern schedulers are preemptive
    - Require the context switch

Response time
- Turnaround time
    - A good metric for a batching system
- Response time
    - More important for an interactive system?
        - User would sit at a terminal, working something interactively
            - E.g) move a mouse, type in a letter, visit a site, and so on

Round Robbin
- Instead of running a job to completion, it runs a job for a time slice(Sometimes called a scheduling quantum) and switch to the next job in the run queue
- Repeatedly switch jobs until jobs are finished

Tradeoff of time quantum
- small : good responsiveness, high context switch overhead
- Large : bad responsiveness, low context switch overhead
- Need to balance the tradeoff

Tradeoff between response time and turnaround time
- Traditional issue in computer science : interactivity vs performance

Incorporating I/O
- most of apps do I/Os
    - Example
        - Two jobs A and B, both need 50ms of CPU time
        - A runs for 10 ms and then issue an I/O request(it takes 10ms)
    - What to do while performing I/Os?
        - Busy waiting
        - Blocked

How to predict the lenght of a job(run time)?
- By user specification
- By prediction(approximation)
    - The CPU time length will be similar in lenght to the previous ones
        - > exponential moving average

MLFQ(Multi-Level Feedback Queue)
- Existing scheduling policies
    - FIFO, SJF, STCF : good for turnaround time, terrible for response time
    - Round Robbin : vice versa
- How to optimize the turnaround time while minimizing response time?
    - Multi-Level Feedback Queue
        - Approach : Learn from the past to predict the future
- Name analysis
    - Multi level : multiple queue
    - Feedback : 
- 
- Problem with our current MLFQ
    - Starvation


## Reference

- boj : [문제집](https://www.acmicpc.net/group/workbook/view/15281/49695)

- lecture : [운영체제](http://www.kocw.net/home/search/kemView.do?kemId=1046323), [OSTEP](https://pages.cs.wisc.edu/~remzi/OSTEP/)
[OSTEP 번역](https://github.com/remzi-arpacidusseau/ostep-translations/blob/master/korean/README.md)
