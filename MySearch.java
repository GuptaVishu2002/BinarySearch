abstract public class MySearch {
    protected int datanum;
    protected int maxnum;
    protected MyData[] data;
    protected int compareCount;

	//MySearchコンストラクタの宣言
    MySearch(int max){
        data = new MyData[max];
        datanum = 0;
        maxnum = max;
    }

	//compareCountの値を０にリセットするためにメソッド
    public void resetCompareCount(){
        compareCount = 0;
    }

	//compareCountのセッタ
    public void setCompareCount(int count){
        compareCount = count;
    }

	//compareCountのゲッタ
    public int getCompareCount(){
        return compareCount;
    }

    abstract public boolean insert(String key, String data);
    abstract public MyData search(String key);
    abstract public MyData searching(int key);
    abstract public int searchIndex(String key);
    abstract public MyData delete(String key);
    
    //配列を出力されるためのメソッド
    public void printData(){
        for(int i = 0; i < datanum; i++){
            System.out.println(data[i].toStringSimple());
        }
    }
    
    //２つのストリング型の変数key1とkey2を比較するためのメソッド
    public int strcmp(String key1, String key2){
        compareCount++;
        return key1.compareTo(key2);
    }
}
