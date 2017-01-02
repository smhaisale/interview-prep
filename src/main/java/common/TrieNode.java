package common;

/**
 * Created by shahbaaz on 12/31/16.
 */
public class TrieNode {
    public String word;
    public TrieNode[] map = new TrieNode[26];

    public TrieNode() {}
    public TrieNode(String s) { word = s; }
}
