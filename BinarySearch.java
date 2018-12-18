public class BinarySearch extends MySearch {
    static private int i;
    
    //BinarySearchコンストラクタの宣言
    BinarySearch(int max){
        super(max);
    }
    
    //配列に新しい文字列の挿入するためのメソッド
    public boolean insert(String key, String data){
        if (maxnum > datanum) {
            MyData newData = new MyData(key, data);
            this.data[datanum] = newData;
            datanum++;
     // 新規データを正しい位置まで移動させる
        for(int i = datanum - 1; i > 0; i--){
            if (strcmp(this.data[i-1].getKey(), this.data[i].getKey()) > 0){
                MyData tmp = this.data[i-1];
                this.data[i-1] = this.data[i];
                this.data[i] = tmp;
            }else{
                break;
            }
        }
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
    
    //search all the detail for a particlar element of array
    public MyData searching(int key){
        int index = key;
        if (index == -1) {
            return null;
        }else{
            return data[index];
        }
    }
    
	//検索された文字列が配列の中でどこに存在するのか(文字列のアドレス)を調べるメソッド
    public int searchIndex(String key){
        int low = 0;
        int high = datanum - 1;
        
        while (low <= high) {
            int middle = (low + high) / 2;
            int status = strcmp(key, data[middle].getKey());
           
            if (status == 0) {      
                return middle;
            } else if (status < 0) {
                high = middle - 1;
            } else {                   
                low = middle + 1;
            }
        }
        return -1;
    }

	//to delete the element of array
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
