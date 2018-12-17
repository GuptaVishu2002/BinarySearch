public class MyData {

    static private int NEXT_ID = 0;
    private int id;
    private String key;
    private String data;

	// MyDataコンストラクタの宣言
    MyData(String key, String data){
        this.id = NEXT_ID;
        NEXT_ID++;
        this.key = key;
        this.data = data;
    }

	//idのゲッタ
    public int getId(){
        return this.id;
    }

	//keyのゲッタ
    public String getKey(){
        return this.key;
    }

	//dataのゲッタ
    public String getData(){
        return this.data;
    }

	//idのセッタ
    public void setId(int id){
        this.id = id;
    }

	//keyのセッタ
    public void setKey(String key){
        this.key = key;
    }
    
	//dataのセッタ
    public void setData(String data){
        this.data = data;
    }

	//文字列を返すためのメソッド
    public String toString(){
        return String.format("[%5d] %s: %s", this.id, this.key, this.data);
    }

	//条件をつけて長さが４１以上の時に簡単に書き換えた文字列を返すためのメソッド
    public String toStringSimple(){
        if (this.data.length()>41){
            return String.format("[%5d] %s: %s...", this.id, this.key, this.data.substring(0,40));
        }else{
            return String.format("[%5d] %s: %s", this.id, this.key, this.data);
        }
    }
}
