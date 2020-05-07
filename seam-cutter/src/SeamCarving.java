import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;

    // Field getters
    int[] getPixels() {
        return pixels;
    }

<<<<<<< HEAD
=======
    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    // Read and write images

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];

        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

    ArrayList<Position> getHVneighbors(int h, int w) {
<<<<<<< HEAD
        ArrayList<Position> getHv = new ArrayList<Position>();
        Position getTop = new Position(h + 1, w);
        Position getBottom = new Position(h - 1, w);
        Position getRight = new Position(h, w + 1);
        Position getLeft = new Position(h, w - 1);

        int counter = 0;
        if (w < 0 || w >= getWidth() || h < 0 || h >= getHeight())
            throw new IndexOutOfBoundsException();
        if (h > 0) {
            getHv.add(counter, new Position(h - 1, w));
            counter += 1;
        }
        if (h < getHeight() - 1) {
            getHv.add(counter, new Position(h + 1, w));
            counter += 1;
        }
        if (w > 0) {
            getHv.add(counter, new Position(h, w - 1));
            counter += 1;
        }
        if (w < getWidth() - 1) {
            getHv.add(counter, new Position(h, w + 1));
            counter += 1;

        }
        return getHv;
=======
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        return neighbors;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();
<<<<<<< HEAD
        Position getDiagLeft = new Position(h + 1, w - 1);
        Position getBottom = new Position(h + 1, w);
        Position getDiagRight = new Position(h + 1, w + 1);
        if (h == getHeight() - 1) {
            return neighbors;
        }else if (h == 0) {
            neighbors.add(getBottom);
            if (w == 0) {
                neighbors.add(getDiagRight);
            }else if (w == getWidth() - 1) {
                neighbors.add(getDiagLeft);
            }else {
                neighbors.add(getDiagLeft);
                neighbors.add(getDiagRight);
            }
//tesr
        }else{
            neighbors.add(getBottom);
            if(w == 0){
                neighbors.add(getDiagRight);
            }
            else if(w == getWidth() - 1){
                neighbors.add(getDiagLeft);
            }else{
                neighbors.add(getDiagRight);
                neighbors.add(getDiagLeft);
            }
        }
        return neighbors;
    }

    /**
     * This method takes the position of a pixel (h,w) and computes its 'energy'
     * which is an estimate of how it differs from its neighbors. The computation
     * is as follows. First, using the method getColor, get the colors of the pixel
     * and all its neighbors in the horizontal and vertical dimensions. The energy
     * is the sum of the squares of the differences along each of the RGB components
     * of the color. For example, given two colors c1 and c2 (for the current pixel
     * and one of its neighbors), we would compute this component of the energy as:
     * square (c1.getRed() - c2.getRed()) +
     * square (c1.getGreen() - c2.getGreen()) +
     * square (c1.getBlue() - c2.getBlue())
     * The total energy is this quantity summed over all the neighbors in the
     * horizontal and vertical dimensions.
     */
    int computeEnergy(int h, int w) {
        ArrayList<Position> neighbors = getHVneighbors(h, w);
        Color current = getColor(h, w);
        int deltaX = 0;
        int red = current.getRed();
        int blue = current.getBlue();
        int green = current.getGreen();
        for (Position elem : neighbors) {
            Color neighbor = getColor(elem.getFirst(), elem.getSecond());
            int nRed = neighbor.getRed();
            int nBlue = neighbor.getBlue();
            int nGreen = neighbor.getGreen();
            double calculate = Math.pow((red - nRed), 2) + Math.pow((blue - nBlue), 2) + Math.pow((green - nGreen), 2);
            deltaX += calculate;
        }
        return deltaX;
    }

    /**
     * This next method is the core of our dynamic programming algorithm. We will
     * use the top-down approach with the given hash table (which you should initialize).
     * The key to the hash table is a pixel position. The value stored at each key
     * is the "seam" that starts with this pixel all the way to the bottom
     * of the image and its cost.
     * <p>
     * The method takes the position of a pixel and returns the seam from this pixel
     * and its cost using the following steps:
     * - compute the energy of the given pixel
     * - get the list of neighbors below the current pixel
     * - Base case: if the list of neighbors is empty, return the following pair:
     * < [<h,w>], energy >
     * the first component of the pair is a list containing just one position
     * (the current one); the second component of the pair is the current energy.
     * - Recursive case: we will consider each of the neighbors below the current
     * pixel and choose the one with the cheapest seam.
     */
    HashMap<Position, Pair<List<Position>, Integer>> hash = new HashMap<>();
    Pair<List<Position>, Integer> findSeam(int h, int w) {
        int getCost = Integer.MAX_VALUE;
        List<Position> bestPath = null;
        int energy = computeEnergy(h, w);
        ArrayList<Position> getNeighbors = this.getBelowNeighbors(h, w);
        Pair<List<Position>, Integer> seam;
        Pair getPair = new Pair<>(List.singleton(new Position(h,w)),energy);

        List<Position> bestNeighbor = new Empty<>();
        if (hash.containsKey(new Position(h, w))) {
            return hash.get(new Position(h, w));
        }
        if (getNeighbors.isEmpty()) {
             hash.put(new Position(h,w),getPair);
        }
        else{
            for(Position elem : getNeighbors){
                Pair<List<Position>, Integer> checkSeam = findSeam(elem.getFirst(), elem.getSecond());
                if(checkSeam.getSecond() < getCost){
                    getCost = checkSeam.getSecond();
                    bestPath = checkSeam.getFirst();
                }
            }
            int totalCost = getCost + computeEnergy(h,w);
            List<Position> totalPath = new Node<>(new Position(h,w),bestPath);
            hash.put(new Position(h,w),new Pair<>(totalPath,totalCost));
        }
        return hash.get(new Position(h,w));
    }

    /**
     * This next method is relatively short. It performs the following actions:
     * - clears the hash table
     * - iterate over the first row of the image, computing the seam
     * from its position and returning the best one.
     */

    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        int getCost = Integer.MAX_VALUE;
        List<Position> getPath = null;
        for(int i = 0; i < width; i++){
            int tempCost = findSeam(0,i).getSecond();
            if(tempCost < getCost){
                getCost = tempCost;
                getPath = findSeam(0,i).getFirst();
            }
        }
        Pair results = new Pair<>(getPath,getCost);
        return results;
    }

    /**
     * The last method puts its all together:
     * - it finds the best seam
     * - then it creates a new array of pixels representing an image of dimensions
     * (height,width-1)
     * - it then copies the old array pixels to the new arrays skipping the pixels
     * in the seam
     * - the method does not return anything: instead it updates the width and
     * pixels instance variables to the new values.
     */
    void cutSeam() {
       try{
           List<Position> seam = bestSeam().getFirst();
           int[] newPixels = new int[(width - 1) * height];
           for(int i = 0; i < height; i++){
               int newWidth = 0;
               for(int j = 0; j < width; j++) {
                   if (seam.isEmpty()) {
                       newPixels[i * (width - 1) + newWidth] = pixels[i * width + j];
                       newWidth++;
                   }
                   else if(seam.getFirst().getFirst() == i && seam.getFirst().getSecond() == j){
                       newWidth = newWidth;
                       seam = seam.getRest();
                   }
                   else{
                    newPixels[i * (width - 1) + newWidth] = pixels[i*width + j];
                    newWidth++;
                   }
               }
           }
           pixels = newPixels;
           width +=1;
       }catch (EmptyListE e){

       }
=======
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
    }

    // Computing energy at given pixel

    int computeEnergy(int h, int w) {
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    // Find seam starting from (h,w) going down and return list of positions and cost
    // and then pick best seam starting from some position on the first row

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return hash.computeIfAbsent(new Position(h, w), p -> {

                    int energy = computeEnergy(h, w);
                    ArrayList<Position> below = getBelowNeighbors(h, w);
                    if (below.isEmpty()) {
                        return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), new Empty<>()),
                                energy);
                    } else {
                        if (below.size() == 1) {
                            Pair<List<Position>, Integer> s = findSeam(below.get(0).getFirst(), below.get(0).getFirst());
                            return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s.getFirst()),
                                    energy + s.getSecond());
                        } else if (below.size() == 2) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            if (s1.getSecond() <= s2.getSecond()) {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                        energy + s1.getSecond());
                            } else {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                        energy + s2.getSecond());
                            }
                        } else if (below.size() == 3) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            Pair<List<Position>, Integer> s3 = findSeam(below.get(2).getFirst(), below.get(2).getSecond());

                            if (s1.getSecond() <= s2.getSecond()) {
                                if (s1.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                            energy + s1.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            } else {
                                if (s2.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                            energy + s2.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            }
                        }
                    }
                    return null;
                });
    }



    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        int cost = Integer.MAX_VALUE;
        List<Position> seam = new Empty<>();
        for (int w = 0; w < width; w++) {
            Pair<List<Position>, Integer> r = findSeam(0, w);
            if (r.getSecond() < cost) {
                seam = r.getFirst();
                cost = r.getSecond();
            }
        }
        return new Pair<>(seam, cost);
    }

    // Putting it all together; find best seam and copy pixels without that seam

    void cutSeam() {
        try {
            List<Position> seam = bestSeam().getFirst();
            int[] newPixels = new int[height * (width - 1)];
            for (int h = 0; h < height; h++) {
                int nw = 0;
                for (int w = 0; w < width; w++) {
                    if (seam.isEmpty()) {
                        newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                    }
                    else {
                        Position p = seam.getFirst();
                        if (p.getFirst() == h && p.getSecond() == w) {
                            seam = seam.getRest();
                            nw--;
                        } else {
                            newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                        }
                    }
                    nw++;
                }
            }
            width = width - 1;
            pixels = newPixels;
        } catch (EmptyListE e) {
            throw new Error("Bug");
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }
}
