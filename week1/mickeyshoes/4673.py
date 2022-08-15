full = set()
constructor = set()

for i in range(1,10001):
    a = [int(j) for j in str(i)]
    a.append(i)
    constructor.add(sum(a))
    full.add(i)

for i in sorted(full-constructor):
    print(i)