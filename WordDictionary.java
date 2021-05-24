import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

    private Map<Character,Trie> root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new HashMap<>();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Map<Character,Trie> temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(temp.containsKey(ch)){
                temp = temp.get(ch).trie;
            }else{
                Trie newNode = new Trie((i == word.length() - 1) ? Boolean.TRUE : Boolean.FALSE);
                temp.put(ch, newNode);
                temp = newNode.trie;
            }
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Map<Character,Trie> root, String word, int index){
        if(index >= word.length()){
            return Boolean.FALSE;
        }
        char ch = word.charAt(index);
            if(ch == '.'){
                for(Map.Entry<Character, Trie> entry : root.entrySet()){
                    if(index == word.length() - 1 && entry.getValue().isWord){
                        return Boolean.TRUE;
                    }
                    else if(search(entry.getValue().trie, word,index + 1)){
                        return Boolean.TRUE;
                    }
                }
            } else if(root.containsKey(ch)){
                if(index == word.length() - 1 && root.get(ch).isWord){
                    return Boolean.TRUE;
                } else {
                    return search(root.get(ch).trie, word,index + 1);

                }
            }
        return Boolean.FALSE;
    }

    private class Trie {
        private boolean isWord;
        private Map<Character,Trie> trie;

        public Trie(boolean isWord){
            this.isWord = isWord;
            trie = new HashMap<>();
        }
    }
}
