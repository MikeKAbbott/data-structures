import javax.sound.sampled.Line;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

class LineWrap {
    private static int lineWidth;
  LineWrap (int lineWidth) {
      this.lineWidth = lineWidth;
  }


  //-------------------------------------------------------------------------
  /**
   * The greedy implementation keeps consuming words from the given list
   * of words while keeping track of how much space is left on the current line.
   * If the current word with a preceding space would fit on the current line, the
   * algorithm continues with the remaining words and an adjusted space.
   * If the word preceded by a space does not fit on the current line, a new line
   * is inserted instead and the algorithm continues with the rest of the words and
   * an adjusted space.
  */
  String greedy (List<String> words, int space){
      String paragraph = "";
      try {
          while(true) {
              int wordLength = words.getFirst().length();
              String first = words.getFirst();
              if (wordLength + 1 <= space) {
                  paragraph = paragraph + " " + first;
                  space = space - (wordLength + 1);
                  words = words.getRest();
              } else {
                  paragraph = paragraph + "\n" + first;
                  space = lineWidth - wordLength;
                  words = words.getRest();
              }
          }
      } catch(NoSuchElementE E){
              return paragraph;
          }
  }

  /**
   * A simple way of running the greedy algorithm
   */
  static String runGreedy (String s, int lineWidth) throws NoSuchElementE {
        List<String> words = List.fromArray(s.split("\\s"));
        LineWrap wrap = new LineWrap(lineWidth);
        String w = words.getFirst();
        String para = w + wrap.greedy(words.getRest(), lineWidth - w.length());
        return para;
    }

  //-------------------------------------------------------------------------

  /** A hash table for use with the top down dynamic programming solution */
  static Map<Pair<List<String>,Integer>,Pair<String,Integer>> hash = new HashMap<>();

  /**
   * The greedy algorithm always adds words to the current line as long as they
   * would fit. The dynamic programming algorithm instead considers two options
   * for each word: add it to the current line, or insert a new line before
   * the word. For each possibility, an estimate of "badness" is calculated
   * and the best option is chosen. The "badness" of a solution is the sum
   * of the cubes of the amount of space left on each line (except the last line).
   * For example, if the line width is 6 and we have the following text:
   *
   * 1234
   * 12 45
   * 123
   * 12
   *
   * then we calculate the badness as follows: the first line has 2 unused spaces
   * at the end, the next line has 1, and the third has 3. The final line is perfect
   * by definition. So the "badness" estimate is:
   * 2^3 + 1^3 + 3^3 = 8 + 1 + 27 = 36
   *
   * In contrast if the text was:
   *
   * 1234
   * 12 45
   * 123 12
   *
   * the "badness" would be: 2^3 + 1^3 = 8 + 1 = 9
   *
   * so we prefer the second arrangement.
   *
   * I strongly suggest you first write a plain recursive solution and test it on the small
   * example (test1). Once that words properly, you can add the hash table management to
   * get your final dynamic programming solution. Without the hash table, the algorithm
   * will really not work on even a moderate size paragraph.
  */

  Pair<String,Integer> dpTD(List<String> words, int space) throws NoSuchElementE {
      Pair<String,Integer>paragraph;
      if(hash.containsKey(new Pair<>(words,space))){
          return hash.get(new Pair<>(words,space));
      }
      try{
          List<String> rest = words.getRest();
          String first = words.getFirst();
          int cube = (int) Math.pow(space,3);
          if(first.length() + 1 > space){
              Pair<String, Integer> newLine = dpTD(rest,lineWidth - first.length());
              paragraph = new Pair<String,Integer>("\n" + first + newLine.getFst(),newLine.getSnd() + cube);
          }else{
              Pair<String,Integer> newLine = dpTD(rest,lineWidth - first.length());
              Pair<String,Integer> sameLine = dpTD(rest,space - (first.length() + 1));
              if(newLine.getSnd() + cube <= sameLine.getSnd()){
                  paragraph = new Pair<String, Integer>("\n" + first + newLine.getFst(),newLine.getSnd() + cube);
              }else{
                  paragraph = new Pair<String, Integer>(" " + first + sameLine.getFst(),sameLine.getSnd());
              }
          }
          hash.put(new Pair<>(words,space),paragraph);
          return paragraph;
          //comment check;
      }catch (NoSuchElementE e){
          hash.put(new Pair<List<String>,Integer>(words,space),new Pair<String, Integer>("",0));
          return hash.get(new Pair<List<String>,Integer>(words,space));
      }
  }


  /**
   * A simple way of running the dynamic programming algorithm
   */
  static String runDP (String s, int lineWidth) throws NoSuchElementE {
      hash = new WeakHashMap<>();
      List<String> words = List.fromArray(s.split("\\s"));
      LineWrap wrap = new LineWrap(lineWidth);
      String w = words.getFirst();
      Pair<String, Integer> sub = wrap.dpTD(words.getRest(), lineWidth - w.length());
      String para = w + sub.getFst();
      return para;
  }

    //-------------------------------------------------------------------------

  /**
   * Here you are to implement the dynamic programming algorithm in a bottom-up fashion.
   * You should still use a hash table as shown below but its management is done
   * "manually" not implicitly when entering / exiting recursive calls.
   */
  static String dpBU (String s, int lineWidth) {
      Map<Pair<Integer, Integer>, Pair<String, Integer>> hash = new HashMap<>();
      String firstPara = "";
      String secondPara = "";
      String[] words = (s.split("\\s"));
      String first = words[0];

      for (int i = 0; i < lineWidth; i++) {
          hash.put(new Pair<Integer, Integer>(words.length, i), new Pair<>("", 0));
      }
      for (int i = words.length - 1; i > 0; i--) {
          for (int space = 0; space <= lineWidth; space++) {

              if (words[i].length() + 1 > space) {
                  Pair<String, Integer> rec = hash.get(new Pair<>(i + 1, lineWidth - words[i].length()));
                  firstPara  = "\n" + words[i] + rec.getFst();
                  hash.put(new Pair<>(i, space), new Pair<>(firstPara , rec.getSnd() + space * space * space));
              } else {
                  Pair<String, Integer> sameLine = hash.get(new Pair<>(i + 1, space - words[i].length() - 1));
                  firstPara  = " " + words[i] + sameLine.getFst();

                  Pair<String, Integer> newLine = hash.get(new Pair<>(i + 1, lineWidth - words[i].length()));
                  secondPara = "\n" + words[i] + newLine.getFst();
                  Pair<String, Integer> results;
                  if (sameLine.getSnd() >= newLine.getSnd() + space * space * space) {
                      results = new Pair<String, Integer>(secondPara, newLine.getSnd() + space * space * space);
                      hash.put(new Pair<Integer, Integer>(i, space), results);
                  } else {
                      results = new Pair<String, Integer>(firstPara , sameLine.getSnd());
                      hash.put(new Pair<Integer, Integer>(i, space), results);
                  }

              }
          }
      }
      String total = hash.get(new Pair<Integer, Integer>(1, lineWidth - first.length())).getFst();
      return first + total;
  }
}
