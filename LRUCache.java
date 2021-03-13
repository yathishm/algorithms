/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * Follow up:
 * Could you do get and put in O(1) time complexity?
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
public class LRUCache {

    private Entry[] table;
    private Entry header;
    private int capacity;
    private int size;

    static class Entry {
        int key;
        int value;
        Entry next;
        Entry before, after;

        Entry(int key, int value){
            this.key = key;
            this.value = value;
        }

        Entry(){ }

        private void addBefore(Entry header){
            this.after = header.after;
            header.after.before = this;
            header.after = this;
            this.before = header;
        }

        private void removeEntry(){
            this.after.before = this.before;
            this.before.after = this.after;
            this.after = this.before = null;
        }
    }

    private void initialize(){
        header.before = header.after = header;
    }

    private int getIndexForKey(int key){
        return key % capacity;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
        header = new Entry();
        initialize();
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or -1 if it contains no mapping for the key.
     * @param key
     * @return
     */
    public int get(int key) {
        int index = getIndexForKey(key);
        Entry entries = table[index];

        for(Entry entry = entries; entry != null; entry = entry.next){
            if(entry.key == key){
                entry.removeEntry();
                entry.addBefore(header);
                return entry.value;
            }
        }
        return -1;
    }

    /**
     *  Associates the specified value with the specified key in this cache.
     *  If it previously contained a mapping for the key, the old value is replaced.
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        int index = getIndexForKey(key);
        Entry entries = table[index];

        for(Entry entry = entries; entry != null; entry = entry.next){
            if(entry.key == key){
                entry.value = value;
                entry.removeEntry();
                entry.addBefore(header);
                return;
            }
        }
        if(size >= capacity){
            removeEldestEntry();
        }
        addEntry(key, value, index);
    }

    /**
     * This method is invoked by <tt>put</tt>
     * If the number of keys exceeds the capacity from put operation, evict the least recently used key
     */
    protected void removeEldestEntry(){
        Entry lastEntry = header.before;

        header.before = header.before.before;
        header.before.after = header;

        int index = getIndexForKey(lastEntry.key);
        Entry entries = table[index];
        Entry prevEntry = null;
        for(Entry entry = entries; entry != null; entry = entry.next){
            if(entry.key == lastEntry.key){
                if(prevEntry != null){
                    prevEntry.next = entry.next;
                }else{
                    table[index] = entry.next;
                }
            }
            prevEntry = entry;
        }
        size--;
    }

    private void addEntry(int key, int value, int index){
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];;
        table[index] = newEntry;
        newEntry.addBefore(header);
        size++;
    }

}
