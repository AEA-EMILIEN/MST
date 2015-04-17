import matplotlib.pyplot as plt

def plotFunction(x,data):
    
    plt.plot([1,2,3,4],[1,4,9,16])
    plt.plot([1,2,3,4],[2,4,6,8])
    plt.ylabel('some numbers')
    plt.show()



def load_data(filename):
    with open(filename) as f:
        content = f.readlines()
    
    x = content[0].split()
    x = range(int(x[0]),int(x[1])+int(x[2]),int(x[2]))
    toto=[]

    for l in content:
       toto+= l.split()
    
    return x,toto

if __name__ == '__main__':
    x,data = load_data("data/Prim")
    
    print data
