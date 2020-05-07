import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

// Using sample paragraphs from
// https://patternbasedwriting.com/elementary_writing_success/paragraph-examples/

public class LineWrapTest {

    String makeLine (int width) {
        char[] chars = new char[width];
        Arrays.fill(chars,'*');
        return new String(chars);
    }
    @Test
    public void failGreed1() throws NoSuchElementE{
        String s = "It may also be objected that my opening remark about the appealing character of Pyrrhonism is wrong or surprising, given that it is not possible for anyone to think that the stance I have presented is attractive and worth adopting. For instance, not only does the Skeptic not promise that the suspensive attitude will certainly make possible the attainment of ataraxia, but he does not even regard this as an aim that is intrinsic to his philosophy. To this objection, I would first reply that the appeal of Skepticism seems to lie in the sort of radical changes that this philosophy may entail in a person’s life. For, if adopted, the cautious Pyrrhonean attitude will prevent one from making rash judgments about any topic that one has not examined or found final answers to, which in turn will prevent one from acting hastily. Another profound change consists in the fact that, even if at some point the Skeptic broke some of the most important moral rules of the society to which he belongs, he would perhaps experience some kind of discomfort, but he would not believe that he has done something objectively wrong. This would free him from the shame and remorse that those who believe that such an action is morally incorrect would experience in the same situation. In sum, the Pyrrhonean philosophy would produce, if adopted, profound changes in a person’s thoughts, feelings, and actions; changes that at first glance seem to be beneficial. But secondly, I think that whether or not Pyrrhonism is an appealing philosophy cannot in the end be determined a priori. For it depends on whether one values such attitudes as caution, open-mindedness, and intellectual modesty; or, if one does, on whether these attitudes are preferred to, for example, the sense of assurance that one may experience when espousing philosophic systems or religious beliefs. This is why my opening comment was just that Pyrrhonism may still be found attractive and worth adopting.";
        String s1 =   LineWrap.runGreedy(s,26);
        String s2 =   LineWrap.runDP(s,26);
        String s3 =   LineWrap.dpBU(s,26);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(26),s1,s2,s3);
    }@Test
    public void failGreed2() throws NoSuchElementE{
        String s = "The Old Man and the Sea tells the story of a battle between an aging, experienced fisherman, Santiago, and a large marlin. The story opens with Santiago having gone 84 days without catching a fish, and now being seen as salao, the worst form of unluckiness. He is so unlucky that his young apprentice, Manolin, has been forbidden by his parents to sail with him and has been told instead to fish with successful fishermen. The boy visits Santiago's shack each night, hauling his fishing gear, preparing food, talking about American baseball and his favorite player, Joe DiMaggio. Santiago tells Manolin that on the next day, he will venture far out into the Gulf Stream, north of Cuba in the Straits of Florida to fish, confident that his unlucky streak is near its end. On the eighty-fifth day of his unlucky streak, Santiago takes his skiff into the Gulf Stream, sets his lines and by noon, has his bait taken by a big fish that he is sure is a marlin. Unable to haul in the great marlin, Santiago is instead pulled by the marlin, and two days and nights pass with Santiago holding onto the line. Though wounded by the struggle and in pain, Santiago expresses a compassionate appreciation for his adversary, often referring to him as a brother. He also determines that, because of the fish's great dignity, no one shall deserve to eat the marlin. On the third day, the fish begins to circle the skiff. Santiago, worn out and almost delirious, uses all his remaining strength to pull the fish onto its side and stab the marlin with a harpoon. Santiago straps the marlin to the side of his skiff and heads home, thinking about the high price the fish will bring him at the market and how many people he will feed. On his way in to shore, sharks are attracted to the marlin's blood. Santiago kills a great mako shark with his harpoon, but he loses the weapon. He makes a new harpoon by strapping his knife to the end of an oar to help ward off the next line of sharks; five sharks are slain and many others are driven away. But the sharks keep coming, and by nightfall the sharks have almost devoured the marlin's entire carcass, leaving a skeleton consisting mostly of its backbone, its tail, and its head. Santiago knows that he is defeated and tells the sharks of how they have killed his dreams. Upon reaching the shore before dawn on the next day, Santiago struggles to his shack, carrying the heavy mast on his shoulder, leaving the fish head and the bones on the shore. Once home, he slumps onto his bed and falls into a deep sleep.";
        String s1 =   LineWrap.runGreedy(s,26);
        String s2 =   LineWrap.runDP(s,26);
        String s3 =   LineWrap.dpBU(s,26);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(34),s1,s2,s3);
    }@Test
    public void failGreed3() throws NoSuchElementE{
        String s = "Everyone that is, except for Manolin. Santiago is Manolina idol and he sticks by him through thick and through thin. I loved how the book ended with Santiago being respected by everyone for catching the biggest marlin anyone had ever seen but still keeping to himself and spending time with the boy. This shows that the old man remembers who stood by his side when times were rough and not just when the village accepted him and that is an attest to the type of person he is. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In my opinion, Santiago represents the ideas of honor and pride. Pride can motivate a man to greatness and that is exactly what happens in this book. His sense of pride would not let him be defeated by the villagers and certainly not out on the boat while wrangling with the marlin for so long. He also upholds honor, which is especially apparent when he is fighting the marlin and doesnâ€™t see it as just a fish but he views it as a worthy opponent. He also fends off the sharks trying to attack his prize catch because he has respect for it. Santiago is a very exceptional man and he stands for all things good. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In conclusion, the hero figure of the novel is indeed the old man, Santiago. He is true, honorable and hard working. He takes nothing for granted and he takes a\n";
        String s1 =   LineWrap.runGreedy(s,17);
        String s2 =   LineWrap.runDP(s,17);
        String s3 =   LineWrap.dpBU(s,17);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(17),s1,s2,s3);
    }
    @Test
    public void test1 () throws NoSuchElementE {
        List<String> words = new NodeL<>("AAA",new NodeL<>("BB",new NodeL<>("CC",new NodeL<>("DDDDD",new EmptyL<>()))));
        String s = "AAA BB CC DDDDD";
        String s1 = LineWrap.runGreedy(s,6);
        System.out.println(s1);
        String s2 = LineWrap.runDP(s,6);
        String s3 = LineWrap.dpBU(s,6);
        System.out.println(s2);
        assertEquals("AAA BB\nCC\nDDDDD", s1);
        assertEquals("AAA\nBB CC\nDDDDD", s2);
        assertEquals("AAA\nBB CC\nDDDDD", s3);
    }

    @Test
    public void test2 () throws NoSuchElementE {
          String s = "AA BB CC DD EEE F GGGGGGGGGGG GGGGGGGG HHHHHHH";
          String s1 = LineWrap.runGreedy(s, 17);
          String s2 = LineWrap.runDP(s, 17);
          System.out.println(s1);
          System.out.println(s2);
          String s3 = LineWrap.dpBU(s, 17);//
         System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(17),s1,s2,s3);
/*
Expected output:

*****************
AA BB CC DD EEE F
GGGGGGGGGGG
GGGGGGGG HHHHHHH

AA BB CC DD EEE
F GGGGGGGGGGG
GGGGGGGG HHHHHHH

AA BB CC DD EEE
F GGGGGGGGGGG
GGGGGGGG HHHHHHH

*/
    }

    @Test
    public void test3 () throws NoSuchElementE {
        String s = "On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space Center in Florida.";
        String s1 = LineWrap.runGreedy(s,31);
        String s2 = LineWrap.runDP(s,31);
        System.out.println(s1);
        System.out.println(s2);
       // String s3 = LineWrap.dpBU(s,31);
        // System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(31),s2);
/*
Expected output:

*******************************
On July 16, 1969, the Apollo 11
spacecraft launched from the
Kennedy Space Center in
Florida.

On July 16, 1969, the Apollo
11 spacecraft launched from
the Kennedy Space Center in
Florida.

On July 16, 1969, the Apollo
11 spacecraft launched from
the Kennedy Space Center in
Florida.

*/
    }

    @Test
    // this example from https://www.rosettacode.org/wiki/Word_wrap
    // designed to make the greedy algorithm look bad
    public void test4 () throws NoSuchElementE {
        String s = "In olden times when wishing still helped one, there lived a king whose daughters were all beautiful, but the youngest was so beautiful that the sun itself, which has seen so much, was astonished whenever it shone-in-her-face.  Close-by-the-king's castle lay a great dark forest, and under an old lime-tree in the forest was a well, and when the day was very warm, the king's child went out into the forest and sat down by the side of the cool-fountain, and when she was bored she took a golden ball, and threw it up on high and caught it, and this ball was her favorite plaything.";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s, 80);
        String s3 = LineWrap.dpBU(s, 80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n", makeLine(80), s1, s2, s3);
/*
Expected output:

********************************************************************************
In olden times when wishing still helped one, there lived a king whose daughters
were all beautiful, but the youngest was so beautiful that the sun itself, which
has seen so much, was astonished whenever it shone-in-her-face.
Close-by-the-king's castle lay a great dark forest, and under an old lime-tree
in the forest was a well, and when the day was very warm, the king's child went
out into the forest and sat down by the side of the cool-fountain, and when she
was bored she took a golden ball, and threw it up on high and caught it, and
this ball was her favorite plaything.

In olden times when wishing still helped one, there lived a king whose
daughters were all beautiful, but the youngest was so beautiful that the sun
itself, which has seen so much, was astonished whenever it shone-in-her-face.
Close-by-the-king's castle lay a great dark forest, and under an old lime-tree
in the forest was a well, and when the day was very warm, the king's child went
out into the forest and sat down by the side of the cool-fountain, and when she
was bored she took a golden ball, and threw it up on high and caught it, and
this ball was her favorite plaything.

In olden times when wishing still helped one, there lived a king whose
daughters were all beautiful, but the youngest was so beautiful that the sun
itself, which has seen so much, was astonished whenever it shone-in-her-face.
Close-by-the-king's castle lay a great dark forest, and under an old lime-tree
in the forest was a well, and when the day was very warm, the king's child went
out into the forest and sat down by the side of the cool-fountain, and when she
was bored she took a golden ball, and threw it up on high and caught it, and
this ball was her favorite plaything.

*/
    }

    @Test
    public void test5 () throws NoSuchElementE {
        String s = "On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space Center in Florida. Its mission was to go where no human being had gone before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic flood plain, on July 20, 1969. The moonwalk took place the following day. On July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from the Lunar Module and took his famous first step onto the moon’s surface. He declared, “That’s one small step for man, one giant leap for mankind.” It was a monumental moment in human history!";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space
Center in Florida. Its mission was to go where no human being had gone
before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz
Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic
flood plain, on July 20, 1969. The moonwalk took place the following day. On
July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from the
Lunar Module and took his famous first step onto the moon’s surface. He
declared, “That’s one small step for man, one giant leap for mankind.” It was a
monumental moment in human history!

On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space
Center in Florida. Its mission was to go where no human being had gone
before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz
Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic
flood plain, on July 20, 1969. The moonwalk took place the following day. On
July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from
the Lunar Module and took his famous first step onto the moon’s surface. He
declared, “That’s one small step for man, one giant leap for mankind.” It was a
monumental moment in human history!

On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space
Center in Florida. Its mission was to go where no human being had gone
before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz
Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic
flood plain, on July 20, 1969. The moonwalk took place the following day. On
July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from
the Lunar Module and took his famous first step onto the moon’s surface. He
declared, “That’s one small step for man, one giant leap for mankind.” It was a
monumental moment in human history!

*/
    }

    @Test
    public void test6 () throws NoSuchElementE {
        String s = "It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he would become the first human being to ever walk on the moon. The journey had begun several days earlier, when on July 16th, the Apollo 11 launched from Earth headed into outer space. On board with Neil Armstrong were Michael Collins and Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before the actual walk. Upon Neil’s first step onto the moon’s surface, he declared, “That’s one small step for man, one giant leap for mankind.” It sure was!";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he
would become the first human being to ever walk on the moon. The journey had
begun several days earlier, when on July 16th, the Apollo 11 launched from Earth
headed into outer space. On board with Neil Armstrong were Michael Collins and
Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before
the actual walk. Upon Neil’s first step onto the moon’s surface, he declared,
“That’s one small step for man, one giant leap for mankind.” It sure was!

It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he
would become the first human being to ever walk on the moon. The journey had
begun several days earlier, when on July 16th, the Apollo 11 launched from Earth
headed into outer space. On board with Neil Armstrong were Michael Collins and
Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before
the actual walk. Upon Neil’s first step onto the moon’s surface, he declared,
“That’s one small step for man, one giant leap for mankind.” It sure was!

It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he
would become the first human being to ever walk on the moon. The journey had
begun several days earlier, when on July 16th, the Apollo 11 launched from Earth
headed into outer space. On board with Neil Armstrong were Michael Collins and
Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before
the actual walk. Upon Neil’s first step onto the moon’s surface, he declared,
“That’s one small step for man, one giant leap for mankind.” It sure was!

*/
    }

    @Test
    public void test7 () throws NoSuchElementE {
        String s = "Here is the perfect system for cleaning your room. First, move all of the items that do not have a proper place to the center of the room. Get rid of at least five things that you have not used within the last year. Take out all of the trash, and place all of the dirty dishes in the kitchen sink. Now find a location for each of the items you had placed in the center of the room. For any remaining items, see if you can squeeze them in under your bed or stuff them into the back of your closet. See, that was easy!";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
Here is the perfect system for cleaning your room. First, move all of the items
that do not have a proper place to the center of the room. Get rid of at least
five things that you have not used within the last year. Take out all of the
trash, and place all of the dirty dishes in the kitchen sink. Now find a
location for each of the items you had placed in the center of the room. For any
remaining items, see if you can squeeze them in under your bed or stuff them
into the back of your closet. See, that was easy!

Here is the perfect system for cleaning your room. First, move all of the
items that do not have a proper place to the center of the room. Get rid of at
least five things that you have not used within the last year. Take out all of
the trash, and place all of the dirty dishes in the kitchen sink. Now find a
location for each of the items you had placed in the center of the room. For
any remaining items, see if you can squeeze them in under your bed or stuff them
into the back of your closet. See, that was easy!

Here is the perfect system for cleaning your room. First, move all of the
items that do not have a proper place to the center of the room. Get rid of at
least five things that you have not used within the last year. Take out all of
the trash, and place all of the dirty dishes in the kitchen sink. Now find a
location for each of the items you had placed in the center of the room. For
any remaining items, see if you can squeeze them in under your bed or stuff them
into the back of your closet. See, that was easy!

*/
    }

    @Test
    public void test8 () throws NoSuchElementE {
        String s = "Oceans and lakes have much in common, but they are also quite different. Both are bodies of water, but oceans are very large bodies of salt water, while lakes are much smaller bodies of fresh water. Lakes are usually surrounded by land, while oceans are what surround continents. Both have plants and animals living in them. The ocean is home to the largest animals on the planet, whereas lakes support much smaller forms of life. When it is time for a vacation, both will make a great place to visit and enjoy.";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
Oceans and lakes have much in common, but they are also quite different. Both
are bodies of water, but oceans are very large bodies of salt water, while lakes
are much smaller bodies of fresh water. Lakes are usually surrounded by land,
while oceans are what surround continents. Both have plants and animals living
in them. The ocean is home to the largest animals on the planet, whereas lakes
support much smaller forms of life. When it is time for a vacation, both will
make a great place to visit and enjoy.

Oceans and lakes have much in common, but they are also quite different. Both
are bodies of water, but oceans are very large bodies of salt water, while lakes
are much smaller bodies of fresh water. Lakes are usually surrounded by land,
while oceans are what surround continents. Both have plants and animals living
in them. The ocean is home to the largest animals on the planet, whereas lakes
support much smaller forms of life. When it is time for a vacation, both will
make a great place to visit and enjoy.

Oceans and lakes have much in common, but they are also quite different. Both
are bodies of water, but oceans are very large bodies of salt water, while lakes
are much smaller bodies of fresh water. Lakes are usually surrounded by land,
while oceans are what surround continents. Both have plants and animals living
in them. The ocean is home to the largest animals on the planet, whereas lakes
support much smaller forms of life. When it is time for a vacation, both will
make a great place to visit and enjoy.

*/
    }

    @Test
    public void test9 () throws NoSuchElementE {
        String s = "The Blue Whales just played their first baseball game of the new season; I believe there is much to be excited about. Although they lost, it was against an excellent team that had won the championship last year. The Blue Whales fell behind early but showed excellent teamwork and came back to tie the game. The team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5 fielding errors, which kept the other team in the lead the entire game. The game ended with the umpire making a bad call, and if the call had gone the other way, the Blue Whales might have actually won the game. It wasn’t a victory, but I say the Blue Whales look like they have a shot at the championship, especially if they continue to improve.";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s, 80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
The Blue Whales just played their first baseball game of the new season; I
believe there is much to be excited about. Although they lost, it was against an
excellent team that had won the championship last year. The Blue Whales fell
behind early but showed excellent teamwork and came back to tie the game. The
team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5
fielding errors, which kept the other team in the lead the entire game. The game
ended with the umpire making a bad call, and if the call had gone the other way,
the Blue Whales might have actually won the game. It wasn’t a victory, but I say
the Blue Whales look like they have a shot at the championship, especially if
they continue to improve.

The Blue Whales just played their first baseball game of the new season; I
believe there is much to be excited about. Although they lost, it was against
an excellent team that had won the championship last year. The Blue Whales fell
behind early but showed excellent teamwork and came back to tie the game. The
team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5
fielding errors, which kept the other team in the lead the entire game. The game
ended with the umpire making a bad call, and if the call had gone the other way,
the Blue Whales might have actually won the game. It wasn’t a victory, but I say
the Blue Whales look like they have a shot at the championship, especially if
they continue to improve.

The Blue Whales just played their first baseball game of the new season; I
believe there is much to be excited about. Although they lost, it was against
an excellent team that had won the championship last year. The Blue Whales fell
behind early but showed excellent teamwork and came back to tie the game. The
team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5
fielding errors, which kept the other team in the lead the entire game. The game
ended with the umpire making a bad call, and if the call had gone the other way,
the Blue Whales might have actually won the game. It wasn’t a victory, but I say
the Blue Whales look like they have a shot at the championship, especially if
they continue to improve.

*/
    }

    @Test
    public void test10 () throws NoSuchElementE {
        String s = "The school fair is right around the corner, and tickets have just gone on sale. We are selling a limited number of tickets at a discount, so move fast and get yours while they are still available. This is going to be an event you will not want to miss! First off, the school fair is a great value when compared with other forms of entertainment. Also, your ticket purchase will help our school, and when you help the school, it helps the entire community. But that’s not all! Every ticket you purchase enters you in a drawing to win fabulous prizes. And don’t forget, you will have mountains of fun because there are acres and acres of great rides, fun games, and entertaining attractions! Spend time with your family and friends at our school fair. Buy your tickets now!";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
The school fair is right around the corner, and tickets have just gone on sale.
We are selling a limited number of tickets at a discount, so move fast and get
yours while they are still available. This is going to be an event you will not
want to miss! First off, the school fair is a great value when compared with
other forms of entertainment. Also, your ticket purchase will help our school,
and when you help the school, it helps the entire community. But that’s not all!
Every ticket you purchase enters you in a drawing to win fabulous prizes. And
don’t forget, you will have mountains of fun because there are acres and acres
of great rides, fun games, and entertaining attractions! Spend time with your
family and friends at our school fair. Buy your tickets now!

The school fair is right around the corner, and tickets have just gone on sale.
We are selling a limited number of tickets at a discount, so move fast and get
yours while they are still available. This is going to be an event you will not
want to miss! First off, the school fair is a great value when compared with
other forms of entertainment. Also, your ticket purchase will help our school,
and when you help the school, it helps the entire community. But that’s not all!
Every ticket you purchase enters you in a drawing to win fabulous prizes. And
don’t forget, you will have mountains of fun because there are acres and acres
of great rides, fun games, and entertaining attractions! Spend time with your
family and friends at our school fair. Buy your tickets now!

The school fair is right around the corner, and tickets have just gone on sale.
We are selling a limited number of tickets at a discount, so move fast and get
yours while they are still available. This is going to be an event you will not
want to miss! First off, the school fair is a great value when compared with
other forms of entertainment. Also, your ticket purchase will help our school,
and when you help the school, it helps the entire community. But that’s not all!
Every ticket you purchase enters you in a drawing to win fabulous prizes. And
don’t forget, you will have mountains of fun because there are acres and acres
of great rides, fun games, and entertaining attractions! Spend time with your
family and friends at our school fair. Buy your tickets now!

*/
    }

    @Test
    public void test11 () throws NoSuchElementE {
        String s = "The school fair is right around the corner, and tickets have just gone on sale. Even though you may be busy, you will still want to reserve just one day out of an entire year to relax and have fun with us. Even if you don’t have much money, you don’t have to worry. A school fair is a community event, and therefore prices are kept low. Perhaps, you are still not convinced. Maybe you feel you are too old for fairs, or you just don’t like them. Well, that’s what my grandfather thought, but he came to last year’s school fair and had this to say about it: “I had the best time of my life!” While it’s true that you may be able to think of a reason not to come, I’m also sure that you can think of several reasons why you must come.  We look forward to seeing you at the school fair!";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
The school fair is right around the corner, and tickets have just gone on sale.
Even though you may be busy, you will still want to reserve just one day out of
an entire year to relax and have fun with us. Even if you don’t have much money,
you don’t have to worry. A school fair is a community event, and therefore
prices are kept low. Perhaps, you are still not convinced. Maybe you feel you
are too old for fairs, or you just don’t like them. Well, that’s what my
grandfather thought, but he came to last year’s school fair and had this to say
about it: “I had the best time of my life!” While it’s true that you may be able
to think of a reason not to come, I’m also sure that you can think of several
reasons why you must come.  We look forward to seeing you at the school fair!

The school fair is right around the corner, and tickets have just gone on sale.
Even though you may be busy, you will still want to reserve just one day out of
an entire year to relax and have fun with us. Even if you don’t have much money,
you don’t have to worry. A school fair is a community event, and therefore
prices are kept low. Perhaps, you are still not convinced. Maybe you feel
you are too old for fairs, or you just don’t like them. Well, that’s what my
grandfather thought, but he came to last year’s school fair and had this to say
about it: “I had the best time of my life!” While it’s true that you may be able
to think of a reason not to come, I’m also sure that you can think of several
reasons why you must come.  We look forward to seeing you at the school fair!

The school fair is right around the corner, and tickets have just gone on sale.
Even though you may be busy, you will still want to reserve just one day out of
an entire year to relax and have fun with us. Even if you don’t have much money,
you don’t have to worry. A school fair is a community event, and therefore
prices are kept low. Perhaps, you are still not convinced. Maybe you feel
you are too old for fairs, or you just don’t like them. Well, that’s what my
grandfather thought, but he came to last year’s school fair and had this to say
about it: “I had the best time of my life!” While it’s true that you may be able
to think of a reason not to come, I’m also sure that you can think of several
reasons why you must come.  We look forward to seeing you at the school fair!

*/
    }

    @Test
    public void test12 () throws NoSuchElementE {
        String s = "Last week we installed a kitty door so that our cat could come and go as she pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the kitty door. We tried pushing her through, and that caused her to be even more afraid. The kitty door was dark, and she couldn’t see what was on the other side. The first step we took in solving this problem was taping the kitty door open. After a couple of days, she was confidently coming and going through the open door. However, when we removed the tape and closed the door, once again, she would not go through. They say you catch more bees with honey, so we decided to use food as bait. We would sit next to the kitty door with a can of wet food and click the top of the can. When kitty came through the closed door, we would open the can and feed her. It took five days of doing this to make her unafraid of using the kitty door. Now we have just one last problem: our kitty controls our lives!";
        String s1 = LineWrap.runGreedy(s, 80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
Last week we installed a kitty door so that our cat could come and go as she
pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the
kitty door. We tried pushing her through, and that caused her to be even more
afraid. The kitty door was dark, and she couldn’t see what was on the other
side. The first step we took in solving this problem was taping the kitty door
open. After a couple of days, she was confidently coming and going through the
open door. However, when we removed the tape and closed the door, once again,
she would not go through. They say you catch more bees with honey, so we decided
to use food as bait. We would sit next to the kitty door with a can of wet food
and click the top of the can. When kitty came through the closed door, we would
open the can and feed her. It took five days of doing this to make her unafraid
of using the kitty door. Now we have just one last problem: our kitty controls
our lives!

Last week we installed a kitty door so that our cat could come and go as she
pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the
kitty door. We tried pushing her through, and that caused her to be even more
afraid. The kitty door was dark, and she couldn’t see what was on the other
side. The first step we took in solving this problem was taping the kitty door
open. After a couple of days, she was confidently coming and going through the
open door. However, when we removed the tape and closed the door, once again,
she would not go through. They say you catch more bees with honey, so we decided
to use food as bait. We would sit next to the kitty door with a can of wet food
and click the top of the can. When kitty came through the closed door, we would
open the can and feed her. It took five days of doing this to make her unafraid
of using the kitty door. Now we have just one last problem: our kitty controls
our lives!

Last week we installed a kitty door so that our cat could come and go as she
pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the
kitty door. We tried pushing her through, and that caused her to be even more
afraid. The kitty door was dark, and she couldn’t see what was on the other
side. The first step we took in solving this problem was taping the kitty door
open. After a couple of days, she was confidently coming and going through the
open door. However, when we removed the tape and closed the door, once again,
she would not go through. They say you catch more bees with honey, so we decided
to use food as bait. We would sit next to the kitty door with a can of wet food
and click the top of the can. When kitty came through the closed door, we would
open the can and feed her. It took five days of doing this to make her unafraid
of using the kitty door. Now we have just one last problem: our kitty controls
our lives!

*/
    }


    @Test
    public void test13 () throws NoSuchElementE {
        String s = "Sunset is the time of day when our sky meets the outer space solar winds. There are blue, pink, and purple swirls, spinning and twisting, like clouds of balloons caught in a whirlwind. The sun moves slowly to hide behind the line of horizon, while the moon races to take its place in prominence atop the night sky. People slow to a crawl, entranced, fully forgetting the deeds that must still be done. There is a coolness, a calmness, when the sun does set.";
        String s1 = LineWrap.runGreedy(s,80);
        String s2 = LineWrap.runDP(s,80);
        String s3 = LineWrap.dpBU(s,80);
        System.out.printf("%s%n%s%n%n%s%n%n%s%n%n%n",makeLine(80),s1,s2,s3);
/*
Expected output:

********************************************************************************
Sunset is the time of day when our sky meets the outer space solar winds. There
are blue, pink, and purple swirls, spinning and twisting, like clouds of
balloons caught in a whirlwind. The sun moves slowly to hide behind the line of
horizon, while the moon races to take its place in prominence atop the night
sky. People slow to a crawl, entranced, fully forgetting the deeds that must
still be done. There is a coolness, a calmness, when the sun does set.

Sunset is the time of day when our sky meets the outer space solar winds.
There are blue, pink, and purple swirls, spinning and twisting, like clouds of
balloons caught in a whirlwind. The sun moves slowly to hide behind the line
of horizon, while the moon races to take its place in prominence atop the night
sky. People slow to a crawl, entranced, fully forgetting the deeds that must
still be done. There is a coolness, a calmness, when the sun does set.

Sunset is the time of day when our sky meets the outer space solar winds.
There are blue, pink, and purple swirls, spinning and twisting, like clouds of
balloons caught in a whirlwind. The sun moves slowly to hide behind the line
of horizon, while the moon races to take its place in prominence atop the night
sky. People slow to a crawl, entranced, fully forgetting the deeds that must
still be done. There is a coolness, a calmness, when the sun does set.

*/
    }


}
