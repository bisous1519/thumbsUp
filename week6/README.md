# week5
- 운영체제 강의

## 추후 일정
```
19일 - 알고리즘 라이브 코딩(SWEA)
22일 - cs
```


## Operating System

### Virtualization

#### Layer of computer system
- computer hardware
- Operating system
    - Hardware abstraction for programmers, users
- System and application programs
    - Webex, powerpoint, compiler
    - Compiler?
        - Translation hign level lang to machine language


#### What happens when program runs?
- CPU(centural processing unit)
- ALU
- Register - memory in cpu
- PC(program counter) - pointing next execution command

- Main memory (e.g Memory)

- I/O Module
    - input/output devices

#### Definition of operating system
- Resource manager(tight definition)
    - Physical resources : Cpu(cores), DRAM, Disk, Flash,…
    - Virtual resources : Process, Thread, Virtual Memory, Page, File…
- Virtualization(Abstraction)
    - E.g) engine

System call
- interfaces (APIs) provided by OS
    - Request for system resources for programs
    - Mode switch
        - User mode
            - Normal mode for cpu
        - Kernel mode
            - Change mode when request system resources

#### Issue for virtualizing CPU
- How to run a new program -> process
- How to make a new process -> fork
- How to select a process to run next? -> scheduling
- How to run multiple processes -> context switching
- How to manage multiple cores(CPU)-> multi-process scheduling, Cache affinity?
- How to allocate memory to process -> malloc(), calloc(), brk()…
- How to protect memory among processes -> virtual memory
- How to implement virtual memory-> page, segment
- How to reduce the overhead of virtual memory -> TLB(Translation Lookup Buffer)

#### Process definition
- A running program, scheduling entity
    - C.f) program - sit on the disk
    - Run on memory and CPU
- exist multiple processes

#### How to virtualize CPU?
- Time sharing on multiple processes
- Machanism(Low level)
    - Context switch : an ability to stop running one program and start running another on a given CPU
- Policy(High level)
    - Scheduling policy : based on historical information or workload knowledge or performance metric

#### Process Structure
- need resources to run
    - CPU
        - Registers such as PC, SP…
    - Memory
        - Text : program codes
        - Data : global variables
        - Stack : local variables, parameters…
        - Heap : allocated dynamically
    - I/O information
        - Openned file (including devices)

#### How to start program?
- Load
    - Bring code and static data into the address space
    - Eagerly vs Lazily? (Paging, swapping)
- Dynamic allocation
    - Stack
    - Initialize parameters(argc, argv)
    - Heap if necessary
- Initialization
    - File descriptors(0, 1, 2)
    - I/O or signal related structure
- Jump to the entry point : main()

#### Process State and transition
- State
    - new(created), ready, running, waiting(blocked), terminated(zombie)
- Transition
    - Admitted, dispatch(schedule), timeout(descheduled, preeemptive), wait(I/O initiate), wakeup(I/O done), exit
    - Suspend and resume : to Disk(swap) or to RAM

#### PCB(Process Control Block)
- Information associated with each process
    - Process state
    - Process ID(pid)
    - Program counter, CPU registers
    - CPU Scheduling information
    - Memory-management information
    - Opened files
    - I/O status information

#### Process API
- fork()
    - Create a new process : parent, child
    - Return two values : one for parent, one for child
    - Non-deterministic
- wait()
    - Block a calling process until one of its children finishes
    - Deterministic -> Synchronization
- exec()
    - Load and overwrite code and static data, re-initialize stack and head, and execute it (never return)
- other APIs
    - Getpid()
    - Kill()
    - Signal()
    - Scheduling related
- command and tool
    - Ps, top, perf, ….


## Reference

- lecture : [운영체제](http://www.kocw.net/home/search/kemView.do?kemId=1046323), [OSTEP](https://pages.cs.wisc.edu/~remzi/OSTEP/)
