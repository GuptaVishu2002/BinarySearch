import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class rp01_B1 {
    static MySearch mySearch;

    static int checkSearchCount;
    static int correctCheckSearchCount;

    // static int checkInsertCount;
    // static int correctCheckInsertCount;
    
    // static int checkDeleteCount;
    // static int correctCheckDeleteCount;


	//ファイルを読み込む処理
    private static void initialize(String filename){
        try {
            FileReader inFile = new FileReader(filename);
            BufferedReader inBuffer = new BufferedReader(inFile);

            String line;
            while ((line = inBuffer.readLine()) != null) {
                commandExecute(line);
            }
            inBuffer.close();
            inFile.close();
        }catch(IOException e){
            System.out.println("Exception :" + e);
        }
    }
    
    //コマンドの設定とそのコマンドが行う処理を指定している
    private static boolean commandExecute(String line){
        StringTokenizer token = new StringTokenizer(line, " ");
        int tokenCount = token.countTokens();
        if (tokenCount > 0){
            String command = new String(token.nextToken());
            if       ((command.equals("insert") || command.equals("ins"))
                      && tokenCount > 2 ){
                /* insert, ins 追加処理*/
                String newKey = new String(token.nextToken());
                StringBuilder newData = new StringBuilder();
                while(token.hasMoreTokens()){
                    newData.append(token.nextToken()); newData.append(" ");
                }
                boolean status = mySearch.insert(newKey, newData.toString());
                if (status == false) return false;
            }else if ((command.equals("delete") || command.equals("del")) 
                      && tokenCount == 2){
                /* delete, del 削除処理*/
                String deleteKey   = new String(token.nextToken());
                MyData result = null;
                result = mySearch.delete(deleteKey);
                if (result != null){
                    System.out.println("  " + result.toStringSimple() + " deleted.");
                }else{
                    System.out.println("  not deleted.");
                }
            }else if ((command.equals("search") || command.equals("find")) 
                      && tokenCount == 2){
                /* search, find 検索処理*/
                String searchKey   = token.nextToken();
                MyData result = mySearch.search(searchKey);
                if (result != null){
                    System.out.println("  key (" + searchKey + ") is found.");
                    System.out.println("  " + result);
                }else{
                    System.out.println("  key (" + searchKey + ") is not found.");
                }
            }else if ((command.equals("allsearch") || command.equals("allfind")) 
                      && tokenCount == 2){
                /* allsearch, allfind 全情報検索処理*/
                String searchKey   = token.nextToken();
                int data           = mySearch.searchIndex(searchKey);
                MyData result      = mySearch.searching(data);
                if (result != null){
                    System.out.println("  key (" + searchKey + ") is found.");
                    System.out.println("  " + result);
                }else{
                    System.out.println("  key (" + searchKey + ") is not found.");
                }
            }else if (command.equals("searchIndex") && tokenCount == 2){
                /* searchIndex 添字検索処理*/
                String searchKey   = token.nextToken();
                int resultIndex = mySearch.searchIndex(searchKey);
                if (resultIndex != -1){
                    System.out.println("  key (" + searchKey + ") is found. Index is " + resultIndex + ".");
                }else{
                    System.out.println("  key (" + searchKey + ") is not found.");
                }
                /*print, show 出力処理*/
            }else if ((command.equals("print") || command.equals("show"))
                      && tokenCount == 1){
                mySearch.printData();
            }else if (command.equals("quit") && tokenCount == 1){
                System.out.println("  quit.");
                return false;
            }else if (command.equals("printCompareCount") && tokenCount == 1){
                System.out.println("  compareCount: " + mySearch.getCompareCount());
            }
            else if (command.equals("search") 
                      && tokenCount == 3){ 

                String searchKey1   = new String(token.nextToken());
                String searchKey2   = new String(token.nextToken());
                int result1         = mySearch.searchIndex(searchKey1);
                int result2         = mySearch.searchIndex(searchKey2);
                System.out.println("  search result between (" + searchKey1 + ") and ("  + searchKey2 + "): ");
                for (int i = result1; i <=result2; i++){
                	MyData result = mySearch.searching(i);
                	String data = result.getKey();
                	System.out.println(i  + " " + data);
                }
			}
            /* for searchIndex() method test */            
            else if (command.equals("checkSearch") 
                      && tokenCount == 3){ 

                String searchKey   = new String(token.nextToken());
                int correctResult  = Integer.parseInt(token.nextToken());
                int result         = mySearch.searchIndex(searchKey);
                System.out.println("  searchIndex(" + searchKey + "): " + result + " (expected output: " + correctResult + ")");
                
                checkSearchCount++;
                if (correctResult == result){
                    correctCheckSearchCount++;
                }
            }else if (command.equals("clearSearchCheck") && tokenCount == 1){
                checkSearchCount = 0;
                correctCheckSearchCount = 0;
                System.out.println("  checkSearchCounter clear");
            }else if (command.equals("printSearchCheck") && tokenCount == 1){
                System.out.print("  checkSearchCounter (" + correctCheckSearchCount + "/" + checkSearchCount + ") ");
                if (correctCheckSearchCount == checkSearchCount) {
                    System.out.println("OK");
                }else{
                    System.out.println("NG");
                }
            }

            else{
                System.out.println("  don't understand your command: " + line);
            }
        }else{
            System.out.println("  don't understand your command: " + line);
        }
        return true;
    }

    public static void main(String argv[]) {
        /* This search is binary search 線形探索の処理*/
        mySearch = new BinarySearch(4096);
        if (argv.length > 0){
            for(int i = 0; i < argv.length; i++){
                long startTime = System.currentTimeMillis();
                initialize(argv[i]);
                long endTime = System.currentTimeMillis();
                System.out.printf("  %7.3f[s] for reading %s.\n",
                                  (endTime - startTime)/1000.0, argv[i]);
            }
        }
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.print("command: ");
                String line = br.readLine();
                if (line == null) break;
                if (commandExecute(line) == false) break;
            }
            br.close();
        }catch(IOException e){
            System.out.println("Exception :" + e);
        }
    }
}
