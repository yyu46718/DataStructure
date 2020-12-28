import java.util.TreeMap;

public class HashTable<K,V> {
  private final int[] capacity
      = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
      49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
      12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

  private static final int UPPERTOL = 10;
  private static final int LOWERTOL = 2;
  private int capacityIndex = 0;

  private TreeMap<K,V> [] hashTable;
  private int size;
  private int M; //hashtbale prime, length of the hashtable

  public HashTable(){
    this.M = capacity[capacityIndex];
    size = 0;
    hashTable = new TreeMap[M];
    for(int i = 0; i<M; i++){
      hashTable[i] = new TreeMap<>();
    }
  }

  public int hash(K key){
    return (key.hashCode() & 0x7fffffff) %M;
  }

  public int getSize(){
    return size;
  }
  public void add(K key, V value){
    TreeMap<K,V> map = hashTable[hash(key)];
    if(map.containsKey(key)){
      map.put(key, value);
    }
    else{
      map.put(key,value);
      size++;
    }
    if(size>=M*UPPERTOL && capacityIndex+1<capacity.length){
      capacityIndex++;
      resize(capacity[capacityIndex]);
    }
  }

  public V remove(K key){
    TreeMap<K,V> map = hashTable[hash(key)];
    V ret = null;
    if(map.containsKey(key)){
      ret = map.remove(key); //don't forget to return the value back to ret
      size--;
    }
    if(size<M*LOWERTOL&&capacityIndex-1>=0){
      capacityIndex--;
      resize(capacity[capacityIndex]);
    }
    return ret;
  }

  private void resize(int newM){
    TreeMap<K,V> [] newHashTable = new TreeMap[newM];
    for(int i = 0; i<newM; i++){
      newHashTable[i] = new TreeMap<>();
    }
    int oldM = M; //saving oldM before reassign this.M bc we want to iterate thru
    this.M = newM; //all oldM but want to use newM in the hash()
    for(int i =0; i<oldM; i++){
      TreeMap<K,V> map = hashTable[i];
      for(K key:map.keySet()){
        newHashTable[hash(key)].put(key, map.get(key));
      }
    }
    this.hashTable = newHashTable; //this.hash is being reassigned
  }

  public void set(K key, V value){
    TreeMap<K,V> map = hashTable[hash(key)];
    if(map.containsKey(key)){
      map.put(key,value);
    }
    else{
      throw new IllegalArgumentException(key + " does not exist");
    }
  }

  public boolean contains(K key){
    TreeMap<K,V> map = hashTable[hash(key)];
    return map.containsKey(key);
  }

  public V get(K key){
    return hashTable[hash(key)].get(key);
  }

}
