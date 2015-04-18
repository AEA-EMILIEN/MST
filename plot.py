import matplotlib.pyplot as plt

def plot2Function(x,dataPrim, dataKruskal):
    
    y = []

    for i in range(1,len(dataPrim)):
        y = dataPrim[i].split()
       
        plt.plot(x,y,"--",label="prim, p="+str((i-1)*2*0.1+0.1))
        y = dataKruskal[i].split()
       
        plt.plot(x,y,"-.",label="kruskal, p="+str((i-1)*2*0.1+0.1))
        
    plt.legend(bbox_to_anchor=(0., 1.), loc=2, borderaxespad=0.)
    plt.xlabel('nombre de sommets dans le graphe')
    plt.ylabel('temps en milliseconde d"execution de l"algorithme')
    plt.show()



def load_data(filename):
    with open(filename) as f:
        content = f.readlines()
    
    x = content[0].split()
    x = range(int(x[0]),int(x[1])+int(x[2]),int(x[2]))
    

    
    return x,content

if __name__ == '__main__':
    x,data = load_data("data/Prim")
    x2,data2 = load_data("data/KruskalTime")

    plot2Function(x,data,data2)
    
