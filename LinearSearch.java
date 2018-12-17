public class LinearSearch extends MySearch {
    static private int i;
    
    //LinearSearchコンストラクタの宣言
    LinearSearch(int max){
        super(max);
    }
    
    //配列に新しい文字列の挿入するためのメソッド
    public boolean insert(String key, String data){
        if (maxnum > datanum) {
            MyData newData = new MyData(key, data);
            this.data[datanum] = newData;
            datanum++;
            return true;
        }else{
            return false;
        }
    }

	//配列から文字列を検索するためのメソッド
    public MyData search(String key){
        int index = searchIndex(key);
        if (index == -1) {
            return null;
        }else{
            return data[index];
        }

    }
    
    //検索された文字列が配列の中でどこに存在するのか（文字列のアドレス）を調べるメソッド
    public int searchIndex(String key){
    	for(int i=0; i < datanum; i++){
        	if (strcmp(data[i].getKey(), key) == 0)
        		return i;
        }	
        return -1;	
    }

	//配列の文字列を削除するためのメソッド
    public MyData delete(String key){
        int deleteDataIndex = searchIndex(key);
        if (deleteDataIndex == -1){
            return null;
        }
        MyData deletedData = data[deleteDataIndex];
        for(int i = deleteDataIndex; i < datanum - 1; i++){
            data[i] = data[i+1];
        }
        datanum--;
        return deletedData;
    }
}
