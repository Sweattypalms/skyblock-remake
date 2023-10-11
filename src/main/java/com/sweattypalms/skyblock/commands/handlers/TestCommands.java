package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.commands.TabCompleter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class TestCommands {
    @Command(name = "test", description = "Test command")
    public void testCommand(Player player) {
        player.sendMessage("This is a test command.\nAnyways, here is the bee movie's script.");
        String script = getBeeMovieScript();
        player.sendMessage(script);
    }

    @TabCompleter(command = "test")
    public List<String> testCompleter(Player player, String[] args) {
        return switch (args.length) {
            case 1 -> List.of("first");
            case 2 -> List.of("second");
            case 3 ->  List.of("third");
            default -> List.of();
        };
    }

    private String getBeeMovieScript() {
        String script =
                "According to all known laws of aviation, there is no way a bee should be able to fly.\n" +
                        "Its wings are too small to get its fat little body off the ground.\n" +
                        "The bee, of course, flies anyway because bees don't care what humans think is impossible.\n" +
                        "Yellow, black. Yellow, black. Yellow, black. Yellow, black.\n" +
                        "Ooh, black and yellow!\n" +
                        "Let's shake it up a little.\n" +
                        "Barry! Breakfast is ready!\n" +
                        "Coming!\n" +
                        "Hang on a second.\n" +
                        "Hello?\n" +
                        "Barry?\n" +
                        "Adam?\n" +
                        "Can you believe this is happening?\n" +
                        "I can't.\n" +
                        "I'll pick you up.\n" +
                        "Looking sharp.\n" +
                        "Use the stairs, Your father paid good money for those.\n" +
                        "Sorry. I'm excited.\n" +
                        "Here's the graduate.\n" +
                        "We're very proud of you, son.\n" +
                        "A perfect report card, all B's.\n" +
                        "Very proud.\n" +
                        "Ma! I got a thing going here.\n" +
                        "You got lint on your fuzz.\n" +
                        "Ow! That's me!\n" +
                        "Wave to us! We'll be in row 118,000.\n" +
                        "Bye!\n" +
                        "Barry, I told you, stop flying in the house!\n" +
                        "Hey, Adam.\n" +
                        "Hey, Barry.\n" +
                        "Is that fuzz gel?\n" +
                        "A little. Special day, graduation.\n" +
                        "Never thought I'd make it.\n" +
                        "Three days grade school, three days high school.\n" +
                        "Those were awkward.\n" +
                        "Three days college. I'm glad I took a day and hitchhiked around The Hive.\n" +
                        "You did come back different.\n" +
                        "Hi, Barry. Artie, growing a mustache? Looks good.\n" +
                        "Hear about Frankie?\n" +
                        "Yeah.\n" +
                        "You going to the funeral?\n" +
                        "No, I'm not going.\n" +
                        "Everybody knows, sting someone, you die.\n" +
                        "Don't waste it on a squirrel.\n" +
                        "Such a hothead.\n" +
                        "I guess he could have just gotten out of the way.\n" +
                        "I love this incorporating an amusement park into our day.\n" +
                        "That's why we don't need vacations.\n" +
                        "Boy, quite a bit of pomp under the circumstances.\n" +
                        "Well, Adam, today we are men.\n" +
                        "We are!\n" +
                        "Bee-men.\n" +
                        "Amen!\n" +
                        "Hallelujah!\n" +
                        "Students, faculty, distinguished bees,\n" +
                        "please welcome Dean Buzzwell.\n" +
                        "Welcome, New Hive City graduating class of 9:15.\n" +
                        "That concludes our ceremonies And begins your career at Honex Industries!\n" +
                        "Will we pick our job today?\n" +
                        "I heard it's just orientation.\n" +
                        "Heads up! Here we go.\n" +
                        "Keep your hands and antennas inside the tram at all times.\n" +
                        "Wonder what it'll be like?\n" +
                        "A little scary.\n" +
                        "Welcome to Honex, a division of Honesco and a part of the Hexagon Group.\n" +
                        "This is it!\n" +
                        "Wow.\n" +
                        "Wow.\n" +
                        "We know that you, as a bee, have worked your whole life to get to the point where you can work for your whole life.\n" +
                        "Honey begins when our valiant Pollen Jocks bring the nectar to The Hive.\n" +
                        "Our top-secret formula is automatically color-corrected, scent-adjusted and bubble-contoured into this soothing sweet syrup with its distinctive golden glow you know as... Honey!\n" +
                        "That girl was hot.\n" +
                        "She's my cousin!\n" +
                        "She is?\n" +
                        "Yes, we're all cousins.\n" +
                        "Right. You're right.\n" +
                        "At Honex, we constantly strive to improve every aspect of bee existence.\n" +
                        "These bees are stress-testing a new helmet technology.\n" +
                        "What do you think he makes?\n" +
                        "Not enough.\n" +
                        "Here we have our latest advancement, the Krelman.\n" +
                        "What does that do?\n" +
                        "Catches that little strand of honey that hangs after you pour it.\n" +
                        "Saves us millions.\n" +
                        "Can anyone work on the Krelman?\n" +
                        "Of course. Most bee jobs are small ones.\n" +
                        "But bees know that every small job, if it's done well, means a lot.\n" +
                        "But choose carefully because you'll stay in the job you pick for the rest of your life.\n" +
                        "The same job the rest of your life? I didn't know that.\n" +
                        "What's the difference?\n" +
                        "You'll be happy to know that bees, as a species, haven't had one day off in 27 million years.\n" +
                        "So you'll just work us to death?\n" +
                        "We'll sure try.\n" +
                        "Wow! That blew my mind!\n" +
                        "\"What's the difference?\"\n" +
                        "How can you say that?\n" +
                        "One job forever?\n" +
                        "That's an insane choice to have to make.\n" +
                        "I'm relieved. Now we only have to make one decision in life.\n" +
                        "But, Adam, how could they never have told us that?\n" +
                        "Why would you question anything? We're bees.\n" +
                        "We're the most perfectly functioning society on Earth.\n" +
                        "You ever think maybe things work a little too well here?\n" +
                        "Like what? Give me one example.\n" +
                        "I don't know. But you know what I'm talking about.\n" +
                        "Please clear the gate. Royal Nectar Force on approach.\n" +
                        "Wait a second. Check it out.\n" +
                        "Hey, those are Pollen Jocks!\n" +
                        "Wow.\n" +
                        "I've never seen them this close.\n" +
                        "They know what it's like outside The Hive.\n" +
                        "Yeah, but some don't come back.\n" +
                        "Hey, Jocks!\n" +
                        "Hi, Jocks!\n" +
                        "You guys did great!\n" +
                        "You're monsters!\n" +
                        "You're sky freaks! I love it! I love it!\n" +
                        "I wonder where they were.\n" +
                        "I don't know.\n" +
                        "Their day's not planned.\n" +
                        "Outside The Hive, flying who knows where, doing who knows what.\n" +
                        "You can't just decide to be a Pollen Jock. You have to be bred for that.\n" +
                        "Right.\n" +
                        "Look. That's more pollen than you and I will see in a lifetime.\n" +
                        "It's just a status symbol.\n" +
                        "Bees make too much of it.\n" +
                        "Perhaps. Unless you're wearing it and the ladies see you wearing it.\n" +
                        "Those ladies?\n" +
                        "Aren't they our cousins too?\n" +
                        "Distant. Distant.\n" +
                        "Look at these two.\n" +
                        "Couple of Hive Harrys.\n" +
                        "Let's have fun with them.\n" +
                        "It must be dangerous being a Pollen Jock.\n" +
                        "Yeah. Once a bear pinned me against a mushroom!\n" +
                        "He had a paw on my throat, and with the other, he was slapping me!\n" +
                        "Oh, my!\n" +
                        "I never thought I'd knock him out.\n" +
                        "What were you doing during this?\n" +
                        "Trying to alert the authorities.\n" +
                        "I can autograph that.\n" +
                        "A little gusty out there today, wasn't it, comrades?\n" +
                        "Yeah. Gusty.\n" +
                        "We're hitting a sunflower patch six miles from here tomorrow.\n" +
                        "Six miles, huh?\n" +
                        "Barry!\n" +
                        "A puddle jump for us, but maybe you're not up for it.\n" +
                        "Maybe I am.\n" +
                        "You are not!\n" +
                        "We're going 0900 at J-Gate.\n" +
                        "What do you think, buzzy-boy?\n" +
                        "Are you bee enough?\n" +
                        "I might be. It all depends on what 0900 means.\n" +
                        "Hey, Honex!\n" +
                        "Dad, you surprised me.\n" +
                        "You decide what you're interested in?\n" +
                        "Well, there's a lot of choices.\n" +
                        "But you only get one.\n" +
                        "Do you ever get bored doing the same job every day?\n" +
                        "Son, let me tell you about stirring.\n" +
                        "You grab that stick, and you just move it around, and you stir it around.\n" +
                        "You get yourself into a rhythm.\n" +
                        "It's a beautiful thing.\n" +
                        "You know, Dad, the more I think about it,\n" +
                        "maybe the honey field just isn't right for me.\n" +
                        "You were thinking of what, making balloon animals?\n" +
                        "That's a bad job for a guy with a stinger.\n" +
                        "Janet, your son's not sure he wants to go into honey!\n" +
                        "Barry, you are so funny sometimes.\n" +
                        "I'm not trying to be funny.\n" +
                        "You're not funny! You're going into honey. Our son, the stirrer!\n" +
                        "You're gonna be a stirrer?\n" +
                        "No one's listening to me!\n" +
                        "Wait till you see the sticks I have.\n" +
                        "I could say anything right now.\n" +
                        "I'm gonna get an ant tattoo!\n" +
                        "Let's open some honey and celebrate!\n" +
                        "Maybe I'll pierce my thorax. Shave my antennae. Shack up with a grasshopper. Get a gold tooth and call everybody \"dawg\"!\n" +
                        "I'm so proud.\n" +
                        "We're starting work today!\n" +
                        "Today's the day.\n" +
                        "Come on! All the good jobs will be gone.\n" +
                        "Yeah, right.\n" +
                        "Pollen counting, stunt bee, pouring, stirrer, front desk, hair removal...\n" +
                        "Is it still available?\n" +
                        "Hang on. Two left!\n" +
                        "One of them's yours! Congratulations!\n" +
                        "Step to the side.\n" +
                        "What'd you get?\n" +
                        "Picking crud out. Stellar!\n" +
                        "Wow!\n" +
                        "Couple of newbies?\n" +
                        "Yes, sir! Our first day! We are ready!\n" +
                        "Make your choice.\n" +
                        "You want to go first?\n" +
                        "No, you go.\n" +
                        "Oh, my. What's available?\n" +
                        "Restroom attendant's open, not for the reason you think.\n" +
                        "Any chance of getting the Krelman?\n" +
                        "Sure, you're on.\n" +
                        "I'm sorry, the Krelman just closed out.\n" +
                        "Wax monkey's always open.\n" +
                        "The Krelman opened up again.\n" +
                        "What happened?\n" +
                        "A bee died. Makes an opening. See? He's dead. Another dead one.\n" +
                        "Deady. Deadified. Two more dead.\n" +
                        "Dead from the neck up. Dead from the neck down. That's life!\n" +
                        "Oh, this is so hard!\n" +
                        "Heating, cooling, stunt bee, pourer, stirrer, humming, inspector number seven, lint coordinator, stripe supervisor, mite wrangler.\n" +
                        "Barry, what do you think I should... Barry?\n" +
                        "Barry!\n" +
                        "All right, we've got the sunflower patch in quadrant nine...\n" +
                        "What happened to you?\n" +
                        "Where are you?\n" +
                        "I'm going out.\n" +
                        "Out? Out where?\n" +
                        "Out there.\n" +
                        "Oh, no!\n" +
                        "I have to, before I go to work for the rest of my life.\n" +
                        "You're gonna die! You're crazy! Hello?\n" +
                        "Another call coming in.\n" +
                        "If anyone's feeling brave, there's a Korean deli on 83rd that gets their roses today.\n" +
                        "Hey, guys.\n" +
                        "Look at that.\n" +
                        "Isn't that the kid we saw yesterday?\n" +
                        "Hold it, son, flight deck's restricted.\n" +
                        "It's OK, Lou. We're gonna take him up.\n" +
                        "Really? Feeling lucky, are you?\n" +
                        "Sign here, here. Just initial that.\n" +
                        "Thank you.\n" +
                        "OK.\n" +
                        "You got a rain advisory today, and as you all know, bees cannot fly in rain.\n" +
                        "So be careful. As always, watch your brooms, hockey sticks, dogs, birds, bears and bats.\n" +
                        "Also, I got a couple of reports of root beer being poured on us.\n" +
                        "Murphy's in a home because of it, babbling like a cicada!\n" +
                        "That's awful.\n" +
                        "And a reminder for you rookies, bee law number one, absolutely no talking to humans!\n" +
                        " All right, launch positions!\n" +
                        "Buzz, buzz, buzz, buzz! Buzz, buzz, buzz, buzz! Buzz, buzz, buzz, buzz!\n" +
                        "Black and yellow!\n" +
                        "Hello!\n" +
                        "You ready for this, hot shot?\n" +
                        "Yeah. Yeah, bring it on.\n" +
                        "Wind, check.\n" +
                        "Antennae, check.\n" +
                        "Nectar pack, check.\n" +
                        "Wings, check.\n" +
                        "Stinger, check.\n" +
                        "Scared out of my shorts, check.\n" +
                        "OK, ladies,\n" +
                        "let's move it out!\n" +
                        "Pound those petunias, you striped stem-suckers!\n" +
                        "All of you, drain those flowers!\n" +
                        "Wow! I'm out!\n" +
                        "I can't believe I'm out!\n" +
                        "So blue.\n" +
                        "I feel so fast and free!\n" +
                        "Box kite!\n" +
                        "Wow!\n" +
                        "Flowers!\n" +
                        "This is Blue Leader, We have roses visual.\n" +
                        "Bring it around 30 degrees and hold.\n" +
                        "Roses!\n" +
                        "30 degrees, roger. Bringing it around.\n" +
                        "Stand to the side, kid.\n" +
                        "It's got a bit of a kick.\n" +
                        "That is one nectar collector!\n" +
                        "Ever see pollination up close?\n" +
                        "No, sir.\n" +
                        "I pick up some pollen here, sprinkle it over here. Maybe a dash over there, a pinch on that one.\n" +
                        "See that? It's a little bit of magic.\n" +
                        "That's amazing. Why do we do that?\n" +
                        "That's pollen power. More pollen, more flowers, more nectar, more honey for us.\n" +
                        "Cool.\n" +
                        "I'm picking up a lot of bright yellow, Could be daisies, Don't we need those?\n" +
                        "Copy that visual.\n" +
                        "Wait. One of these flowers seems to be on the move.\n" +
                        "Say again? You're reporting a moving flower?\n" +
                        "Affirmative.\n" +
                        "That was on the line!\n" +
                        "This is the coolest. What is it?\n" +
                        "I don't know, but I'm loving this color.\n" +
                        "It smells good.\n" +
                        "Not like a flower, but I like it.\n" +
                        "Yeah, fuzzy.\n" +
                        "Chemical-y.\n" +
                        "Careful, guys. It's a little grabby.\n" +
                        "My sweet lord of bees!\n" +
                        "Candy-brain, get off there!\n" +
                        "Problem!\n" +
                        "Guys!\n" +
                        "This could be bad.\n" +
                        "Affirmative.\n" +
                        "Very close.\n" +
                        "Gonna hurt.\n" +
                        "Mama's little boy.\n" +
                        "You are way out of position, rookie!\n" +
                        "Coming in at you like a missile!\n" +
                        "Help me!\n" +
                        "I don't think these are flowers.\n" +
                        "Should we tell him?\n" +
                        "I think he knows.\n" +
                        "What is this?!\n" +
                        "Match point!\n" +
                        "You can start packing up, honey, because you're about to eat it!\n" +
                        "Yowser!\n" +
                        "Gross.\n" +
                        "There's a bee in the car!\n" +
                        "Do something!\n" +
                        "I'm driving!\n" +
                        "Hi, bee.\n" +
                        "He's back here!\n" +
                        "He's going to sting me!\n" +
                        "Nobody move. If you don't move, he won't sting you. Freeze!\n" +
                        "He blinked!\n" +
                        "Spray him, Granny!\n" +
                        "What are you doing?!\n" +
                        "Wow... the tension level out here is unbelievable.\n" +
                        "I gotta get home.\n" +
                        "Can't fly in rain. Can't fly in rain. Can't fly in rain.\n" +
                        "Mayday! Mayday! Bee going down!\n" +
                        "Ken, could you close the window please?\n" +
                        "Ken, could you close the window please?\n" +
                        "Check out my new resume. I made it into a fold-out brochure. You see? Folds out.\n" +
                        "Oh, no. More humans. I don't need this.\n" +
                        "What was that?\n" +
                        "Maybe this time. This time. This time. This time! This time! This... Drapes!\n" +
                        "That is diabolical.\n" +
                        "It's fantastic. It's got all my special skills, even my top-ten favorite movies.\n" +
                        "What's number one? Star Wars?\n" +
                        "Nah, I don't go for that... kind of stuff.\n" +
                        "No wonder we shouldn't talk to them. They're out of their minds.\n" +
                        "When I leave a job interview, they're flabbergasted, can't believe what I say.\n" +
                        "There's the sun. Maybe that's a way out.\n" +
                        "I don't remember the sun having a big 75 on it.\n" +
                        "I predicted global warming. I could feel it getting hotter. At first I thought it was just me.\n" +
                        "Wait! Stop! Bee!\n" +
                        "Stand back. These are winter boots.\n" +
                        "Wait!\n" +
                        "Don't kill him!\n" +
                        "You know I'm allergic to them! This thing could kill me!\n" +
                        "Why does his life have less value than yours?\n" +
                        "Why does his life have any less value than mine? Is that your statement?\n" +
                        "I'm just saying all life has value. You don't know what he's capable of feeling.\n" +
                        "My brochure!\n" +
                        "There you go, little guy.\n" +
                        "I'm not scared of him.It's an allergic thing.\n" +
                        " Put that on your resume brochure.\n" +
                        "My whole face could puff up.\n" +
                        "Make it one of your special skills.\n" +
                        "Knocking someone out is also a special skill.\n" +
                        "Right. Bye, Vanessa. Thanks.\n" +
                        "Vanessa, next week? Yogurt night?\n" +
                        "Sure, Ken. You know, whatever.\n" +
                        "You could put carob chips on there.\n" +
                        "Bye.\n" +
                        "Supposed to be less calories.\n" +
                        "Bye.\n" +
                        "I gotta say something. She saved my life. I gotta say something.\n" +
                        "All right, here it goes.\n" +
                        "Nah.\n" +
                        "What would I say?\n" +
                        "I could really get in trouble. It's a bee law. You're not supposed to talk to a human.\n" +
                        "I can't believe I'm doing this. I've got to.\n" +
                        "Oh, I can't do it. Come on!\n" +
                        "No. Yes. No. Do it. I can't.\n" +
                        "How should I start it? \"You like jazz?\" No, that's no good.\n" +
                        "Here she comes! Speak, you fool!\n" +
                        "Hi!\n" +
                        "I'm sorry. You're talking.\n" +
                        "Yes, I know.\n" +
                        "You're talking!\n" +
                        "I'm so sorry.\n" +
                        "No, it's OK. It's fine.\n" +
                        "I know I'm dreaming. But I don't recall going to bed.\n" +
                        "Well, I'm sure this is very disconcerting.\n" +
                        "This is a bit of a surprise to me. I mean, you're a bee!\n" +
                        "I am. And I'm not supposed to be doing this, but they were all trying to kill me.\n" +
                        "And if it wasn't for you... I had to thank you. It's just how I was raised.\n" +
                        "That was a little weird. I'm talking with a bee.\n" +
                        "Yeah.\n" +
                        "I'm talking to a bee. And the bee is talking to me!\n" +
                        "I just want to say I'm grateful.\n" +
                        "I'll leave now.\n" +
                        "Wait! How did you learn to do that?\n" +
                        "What?\n" +
                        "The talking thing.\n" +
                        "Same way you did, I guess. \"Mama, Dada, honey.\" You pick it up.\n" +
                        "That's very funny.\n" +
                        "Yeah.\n" +
                        "Bees are funny. If we didn't laugh, we'd cry with what we have to deal with.\n" +
                        "Anyway... Can I... get you something?\n" +
                        "Like what?\n" +
                        "I don't know. I mean... I don't know. Coffee?\n" +
                        "I don't want to put you out.\n" +
                        "It's no trouble. It takes two minutes.\n" +
                        "It's just coffee.\n" +
                        "I hate to impose.\n" +
                        "Don't be ridiculous!\n" +
                        "Actually, I would love a cup.\n" +
                        "Hey, you want rum cake?\n" +
                        "I shouldn't.\n" +
                        "Have some.\n" +
                        "No, I can't.\n" +
                        "Come on!\n" +
                        "I'm trying to lose a couple micrograms.\n" +
                        "Where?\n" +
                        "These stripes don't help.\n" +
                        "You look great!\n" +
                        "I don't know if you know anything about fashion.\n" +
                        "Are you all right?\n" +
                        "No.\n" +
                        "He's making the tie in the cab as they're flying up Madison.\n" +
                        "He finally gets there.\n" +
                        "He runs up the steps into the church.\n" +
                        "The wedding is on.\n" +
                        "And he says, \"Watermelon?\n" +
                        "I thought you said Guatemalan.\n" +
                        "Why would I marry a watermelon?\"\n" +
                        "Is that a bee joke?\n" +
                        "That's the kind of stuff we do.\n" +
                        "Yeah, different.\n" +
                        "So, what are you gonna do, Barry?\n" +
                        "About work? I don't know.\n" +
                        "I want to do my part for The Hive, but I can't do it the way they want.\n" +
                        "I know how you feel.\n" +
                        "You do?\n" +
                        "Sure.\n" +
                        "My parents wanted me to be a lawyer or a doctor, but I wanted to be a florist.\n" +
                        "Really?\n" +
                        "My only interest is flowers.\n" +
                        "Our new queen was just elected with that same campaign slogan.\n" +
                        "Anyway, if you look... There's my hive right there. See it?\n" +
                        "You're in Sheep Meadow!\n" +
                        "Yes! I'm right off the Turtle Pond!\n" +
                        "No way! I know that area. I lost a toe ring there once.\n" +
                        "Why do girls put rings on their toes?\n" +
                        "Why not?\n" +
                        "It's like putting a hat on your knee.\n" +
                        "Maybe I'll try that.\n" +
                        "You all right, ma'am?\n" +
                        "Oh, yeah. Fine.\n" +
                        "Just having two cups of coffee!\n" +
                        "Anyway, this has been great.\n" +
                        "Thanks for the coffee.\n" +
                        "Yeah, it's no trouble.\n" +
                        "Sorry I couldn't finish it. If I did, I'd be up the rest of my life.\n" +
                        "Are you...?\n" +
                        "Can I take a piece of this with me?\n" +
                        "Sure! Here, have a crumb.\n" +
                        "Thanks!\n" +
                        "Yeah.\n" +
                        "All right. Well, then... I guess I'll see you around. Or not.\n" +
                        "OK, Barry.\n" +
                        "And thank you so much again... for before.\n" +
                        "Oh, that? That was nothing.\n" +
                        "Well, not nothing, but... Anyway...\n" +
                        "This can't possibly work.\n" +
                        "He's all set to go.\n" +
                        "We may as well try it.\n" +
                        "OK, Dave, pull the chute.\n" +
                        "Sounds amazing.\n" +
                        "It was amazing!\n" +
                        "It was the scariest, happiest moment of my life.\n" +
                        "Humans! I can't believe you were with humans!\n" +
                        "Giant, scary humans!\n" +
                        "What were they like?\n" +
                        "Huge and crazy. They talk crazy.\n" +
                        "They eat crazy giant things.\n" +
                        "They drive crazy.\n" +
                        "Do they try and kill you, like on TV?\n" +
                        "Some of them. But some of them don't.\n" +
                        "How'd you get back?\n" +
                        "Poodle.\n" +
                        "You did it, and I'm glad. You saw whatever you wanted to see.\n" +
                        "You had your \"experience.\" Now you can pick out yourjob and be normal.\n" +
                        "Well...\n" +
                        "Well?\n" +
                        "Well, I met someone.\n" +
                        "You did? Was she Bee-ish?\n" +
                        "A wasp?! Your parents will kill you!\n" +
                        "No, no, no, not a wasp.\n" +
                        "Spider?\n" +
                        "I'm not attracted to spiders.\n" +
                        "I know it's the hottest thing, with the eight legs and all. I can't get by that face.\n" +
                        "So who is she?\n" +
                        "She's... human.\n" +
                        "No, no. That's a bee law. You wouldn't break a bee law.\n" +
                        "Her name's Vanessa.\n" +
                        "Oh, boy.\n" +
                        "She's so nice. And she's a florist!\n" +
                        "Oh, no! You're dating a human florist!\n" +
                        "We're not dating.\n" +
                        "You're flying outside The Hive, talking to humans that attack our homes with power washers and M-80s! One-eighth a stick of dynamite!\n" +
                        "She saved my life! And she understands me.\n" +
                        "This is over!\n" +
                        "Eat this.\n" +
                        "This is not over! What was that?\n" +
                        "They call it a crumb.\n" +
                        "It was so stingin' stripey!\n" +
                        "And that's not what they eat.\n" +
                        "That's what falls off what they eat!\n" +
                        "You know what a Cinnabon is?\n" +
                        "No.\n" +
                        "It's bread and cinnamon and frosting. They heat it up...\n" +
                        "Sit down!\n" +
                        "...really hot!\n" +
                        "Listen to me!\n" +
                        "We are not them! We're us.\n" +
                        "There's us and there's them!\n" +
                        "Yes, but who can deny the heart that is yearning?\n" +
                        "There's no yearning. Stop yearning. Listen to me!\n" +
                        "You have got to start thinking bee, my friend. Thinking bee!\n" +
                        "Thinking bee.\n" +
                        "Thinking bee.\n" +
                        "Thinking bee! Thinking bee! Thinking bee! Thinking bee!\n" +
                        "There he is. He's in the pool.\n" +
                        "You know what your problem is, Barry?\n" +
                        "I gotta start thinking bee?\n" +
                        "How much longer will this go on?\n" +
                        "It's been three days! Why aren't you working?\n" +
                        "I've got a lot of big life decisions to think about.\n" +
                        "What life? You have no life!\n" +
                        "You have no job. You're barely a bee!\n" +
                        "Would it kill you to make a little honey?\n" +
                        "Barry, come out. Your father's talking to you.\n" +
                        "Martin, would you talk to him?\n" +
                        "Barry, I'm talking to you!\n" +
                        "You coming?\n" +
                        "Got everything?\n" +
                        "All set!\n" +
                        "Go ahead. I'll catch up.\n" +
                        "Don't be too long.\n" +
                        "Watch this!\n" +
                        "Vanessa!\n" +
                        "We're still here.\n" +
                        "I told you not to yell at him.\n" +
                        "He doesn't respond to yelling!\n" +
                        "Then why yell at me?\n" +
                        "Because you don't listen!\n" +
                        "I'm not listening to this.\n" +
                        "Sorry, I've gotta go.\n" +
                        "Where are you going?\n" +
                        "I'm meeting a friend.\n" +
                        "A girl? Is this why you can't decide?\n" +
                        "Bye.\n" +
                        "I just hope she's Bee-ish.\n" +
                        "They have a huge parade of flowers every year in Pasadena?\n" +
                        "To be in the Tournament of Roses, that's every florist's dream!\n" +
                        "Up on a float, surrounded by flowers, crowds cheering.\n" +
                        "A tournament. Do the roses compete in athletic events?\n" +
                        "No. All right, I've got one.\n" +
                        "How come you don't fly everywhere?\n" +
                        "It's exhausting. Why don't you run everywhere? It's faster.\n" +
                        "Yeah, OK, I see, I see.\n" +
                        "All right, your turn.\n" +
                        "TiVo. You can just freeze live TV? That's insane!\n" +
                        "You don't have that?\n" +
                        "We have Hivo, but it's a disease. It's a horrible, horrible disease.\n" +
                        "Oh, my.\n" +
                        "Dumb bees!\n" +
                        "You must want to sting all those jerks.\n" +
                        "We try not to sting. It's usually fatal for us.\n" +
                        "So you have to watch your temper.\n" +
                        "Very carefully.\n" +
                        "You kick a wall, take a walk, write an angry letter and throw it out. Work through it like any emotion: Anger, jealousy, lust.\n" +
                        "Oh, my goodness! Are you OK?\n" +
                        "Yeah.\n" +
                        "What is wrong with you?!\n" +
                        "It's a bug.\n" +
                        "He's not bothering anybody.\n" +
                        "Get out of here, you creep!\n" +
                        "What was that? A Pic 'N' Save circular?\n" +
                        "Yeah, it was. How did you know?\n" +
                        "It felt like about 10 pages. Seventy-five is pretty much our limit.\n" +
                        "You've really got that down to a science.\n" +
                        "I lost a cousin to Italian Vogue.\n" +
                        "I'll bet.\n" +
                        "What in the name of Mighty Hercules is this?\n" +
                        "How did this get here? cute Bee, Golden Blossom, Ray Liotta Private Select?\n" +
                        "Is he that actor?\n" +
                        "I never heard of him.\n" +
                        "Why is this here?\n" +
                        "For people. We eat it.\n" +
                        "You don't have enough food of your own?\n" +
                        "Well, yes.\n" +
                        "How do you get it?\n" +
                        "Bees make it.\n" +
                        "I know who makes it! And it's hard to make it!\n" +
                        "There's heating, cooling, stirring. You need a whole Krelman thing!\n" +
                        "It's organic.\n" +
                        "It's our-ganic!\n" +
                        "It's just honey, Barry.\n" +
                        "Just what?!\n" +
                        "Bees don't know about this! This is stealing! A lot of stealing!\n" +
                        "You've taken our homes, schools,hospitals! This is all we have!\n" +
                        "And it's on sale?! I'm getting to the bottom of this.\n" +
                        "I'm getting to the bottom of all of this!\n" +
                        "Hey, Hector. You almost done?\n" +
                        "Almost.\n" +
                        "He is here. I sense it.\n" +
                        "Well, I guess I'll go home now and just leave this nice honey out, with no one around.\n" +
                        "You're busted, box boy!\n" +
                        "I knew I heard something.\n" +
                        "So you can talk!\n" +
                        "I can talk. And now you'll start talking!\n" +
                        "Where you getting the sweet stuff? Who's your supplier?\n" +
                        "I don't understand.\n" +
                        "I thought we were friends.\n" +
                        "The last thing we want to do is upset bees!\n" +
                        "You're too late! It's ours now!\n" +
                        "You, sir, have crossed the wrong sword!\n" +
                        "You, sir, will be lunch for my iguana, Ignacio!\n" +
                        "Where is the honey coming from? Tell me where!\n" +
                        "Honey Farms! It comes from Honey Farms!\n" +
                        "Crazy person!\n" +
                        "What horrible thing has happened here?\n" +
                        "These faces, they never knew what hit them. And now\n" +
                        "they're on the road to nowhere!\n" +
                        "Just keep still.\n" +
                        "What? You're not dead?\n" +
                        "Do I look dead? They will wipe anything that moves. Where you headed?\n" +
                        "To Honey Farms. I am onto something huge here.\n" +
                        "I'm going to Alaska. Moose blood, crazy stuff. Blows your head off!\n" +
                        "I'm going to Tacoma.\n" +
                        "And you?\n" +
                        "He really is dead.\n" +
                        "All right.\n" +
                        "Uh-oh!\n" +
                        "What is that?!\n" +
                        "Oh, no!\n" +
                        "A wiper! Triple blade!\n" +
                        "Triple blade?\n" +
                        "Jump on! It's your only chance, bee!\n" +
                        "Why does everything have\n" +
                        "to be so doggone clean?!\n" +
                        "How much do you people need to see?!\n" +
                        "Open your eyes!\n" +
                        "Stick your head out the window!\n" +
                        "From NPR News in Washington,\n" +
                        "I'm Carl Kasell.\n" +
                        "But don't kill no more bugs!\n" +
                        "Bee!\n" +
                        "Moose blood guy!!\n" +
                        "You hear something?\n" +
                        "Like what?\n" +
                        "Like tiny screaming.\n" +
                        "Turn off the radio.\n" +
                        "Whassup, bee boy?\n" +
                        "Hey, Blood.\n" +
                        "Just a row of honey jars, as far as the eye could see.\n" +
                        "Wow!\n" +
                        "I assume wherever this truck goes is where they're getting it. I mean, that honey's ours.\n" +
                        "Bees hang tight. We're all jammed in.\n" +
                        "It's a close community.\n" +
                        "Not us, man. We on our own. Every mosquito on his own.\n" +
                        "What if you get in trouble?\n" +
                        "You a mosquito, you in trouble. Nobody likes us. They just smack. See a mosquito, smack, smack!\n" +
                        "At least you're out in the world. You must meet girls.\n" +
                        "Mosquito girls try to trade up, get with a moth, dragonfly. Mosquito girl don't want no mosquito.\n" +
                        "You got to be kidding me!\n" +
                        "Mooseblood's about to leave the building! So long, bee!\n" +
                        "Hey, guys!\n" +
                        "Mooseblood!\n" +
                        "I knew I'd catch y'all down here.\n" +
                        "Did you bring your crazy straw?\n" +
                        "We throw it in jars, slap a label on it, and it's pretty much pure profit.\n" +
                        "What is this place?\n" +
                        "A bee's got a brain the size of a pinhead.\n" +
                        "They are pinheads!\n" +
                        "Pinhead.\n" +
                        "Check out the new smoker.\n" +
                        "Oh, sweet. That's the one you want. The Thomas 3000!\n" +
                        "Smoker?\n" +
                        "Ninety puffs a minute, semi-automatic. Twice the nicotine, all the tar. A couple breaths of this knocks them right out.\n" +
                        "They make the honey, and we make the money.\n" +
                        "\"They make the honey, and we make the money\"?\n" +
                        "Oh, my!\n" +
                        "What's going on? Are you OK?\n" +
                        "Yeah. It doesn't last too long.\n" +
                        "Do you know you're in a fake hive with fake walls?\n" +
                        "Our queen was moved here. We had no choice.\n" +
                        "This is your queen? That's a man in women's clothes! That's a drag queen!\n" +
                        "What is this?\n" +
                        "Oh, no!\n" +
                        "There's hundreds of them!\n" +
                        "Bee honey.\n" +
                        "Our honey is being brazenly stolen on a massive scale!\n" +
                        "This is worse than anything bears have done! I intend to do something.\n" +
                        "Oh, Barry, stop.\n" +
                        "Who told you humans are taking our honey? That's a rumor.\n" +
                        "Do these look like rumors?\n" +
                        "That's a conspiracy theory. These are obviously doctored photos. How did you get mixed up in this?\n" +
                        "He's been talking to humans.\n" +
                        "What? Talking to humans?!\n" +
                        "He has a human girlfriend. And they make out!\n" +
                        "Make out? Barry!\n" +
                        "We do not.\n" +
                        "You wish you could.\n" +
                        "Whose side are you on?\n" +
                        "The bees!\n" +
                        "I dated a cricket once in San Antonio. Those crazy legs kept me up all night.\n" +
                        "Barry, this is what you want to do with your life?\n" +
                        "I want to do it for all our lives. Nobody works harder than bees!\n" +
                        "Dad, I remember you coming home so overworked\n" +
                        "your hands were still stirring. You couldn't stop.\n" +
                        "I remember that.\n" +
                        "What right do they have to our honey?\n" +
                        "We live on two cups a year. They put it in lip balm for no reason whatsoever!\n" +
                        "Even if it's true, what can one bee do?\n" +
                        "Sting them where it really hurts.\n" +
                        "In the face! The eye!\n" +
                        "That would hurt.\n" +
                        "No.\n" +
                        "Up the nose? That's a killer.\n" +
                        "There's only one place you can sting the humans, one place where it matters.\n" +
                        "Hive at Five, The Hive's only full-hour action news source.\n" +
                        "No more bee beards!\n" +
                        "With Bob Bumble at the anchor desk. Weather with Storm Stinger. Sports with Buzz Larvi. And Jeanette Chung.\n" +
                        "Good evening. I'm Bob Bumble.\n" +
                        "And I'm Jeanette Ohung.\n" +
                        "A tri-county bee, Barry Benson, intends to sue the human race for stealing our honey, packaging it and profiting from it illegally!\n" +
                        "Tomorrow night on Bee Larry King, we'll have three former queens here in our studio, discussing their new book, classy Ladies, out this week on Hexagon.\n" +
                        "Tonight we're talking to Barry Benson.\n" +
                        "Did you ever think, \"I'm a kid from The Hive. I can't do this\"?\n" +
                        "Bees have never been afraid to change the world.\n" +
                        "What about Bee Oolumbus? Bee Gandhi? Bejesus?\n" +
                        "Where I'm from, we'd never sue humans.\n" +
                        "We were thinking of stickball or candy stores.\n" +
                        "How old are you?\n" +
                        "The bee community is supporting you in this case, which will be the trial of the bee century.\n" +
                        "You know, they have a Larry King in the human world too.\n" +
                        "It's a common name. Next week...\n" +
                        "He looks like you and has a show and suspenders and colored dots...\n" +
                        "Next week...\n" +
                        "Glasses, quotes on the bottom from the guest even though you just heard 'em.\n" +
                        "Bear Week next week! They're scary, hairy and here live.\n" +
                        "Always leans forward, pointy shoulders, squinty eyes, very Jewish.\n" +
                        "In tennis, you attack at the point of weakness!\n" +
                        "It was my grandmother, Ken. She's 81.\n" +
                        "Honey, her backhand's a joke!\n" +
                        "I'm not gonna take advantage of that?\n" +
                        "Quiet, please.\n" +
                        "Actual work going on here.\n" +
                        "Is that that same bee?\n" +
                        "Yes, it is!\n" +
                        "I'm helping him sue the human race.\n" +
                        "Hello.\n" +
                        "Hello, bee.\n" +
                        "This is Ken.\n" +
                        "Yeah, I remember you. Timberland, size ten and a half. Vibram sole, I believe.\n" +
                        "Why does he talk again?\n" +
                        "Listen, you better go 'cause we're really busy working.\n" +
                        "But it's our yogurt night!\n" +
                        "Bye-bye.\n" +
                        "Why is yogurt night so difficult?!\n" +
                        "You poor thing. You two have been at this for hours!\n" +
                        "Yes, and Adam here has been a huge help.\n" +
                        "Frosting...\n" +
                        "How many sugars?\n" +
                        "Just one. I try not to use the competition.\n" +
                        "So why are you helping me?\n" +
                        "Bees have good qualities. And it takes my mind off the shop. Instead of flowers, people are giving balloon bouquets now.\n" +
                        "Those are great, if you're three.\n" +
                        "And artificial flowers.\n" +
                        "Oh, those just get me psychotic!\n" +
                        "Yeah, me too.\n" +
                        "Bent stingers, pointless pollination.\n" +
                        "Bees must hate those fake things!\n" +
                        "Nothing worse than a daffodil that's had work done.\n" +
                        "Maybe this could make up for it a little bit.\n" +
                        "This lawsuit's a pretty big deal.\n" +
                        "I guess.\n" +
                        "You sure you want to go through with it?\n" +
                        "Am I sure? When I'm done with the humans, they won't be able to say, \"Honey, I'm home,\" without paying a royalty!\n" +
                        "It's an incredible scene here in downtown Manhattan, where the world anxiously waits, because for the first time in history, we will hear for ourselves if a honeybee can actually speak.\n" +
                        "What have we gotten into here, Barry?\n" +
                        "It's pretty big, isn't it?\n" +
                        "I can't believe how many humans don't work during the day.\n" +
                        "You think billion-dollar multinational food companies have good lawyers?\n" +
                        "Everybody needs to stay behind the barricade.\n" +
                        "What's the matter?\n" +
                        "I don't know, I just got a chill.\n" +
                        "Well, if it isn't the bee team.\n" +
                        "You boys work on this?\n" +
                        "All rise! The Honorable Judge Bumbleton presiding.\n" +
                        "All right. Case number 4475,\n" +
                        "Superior Court of New York,\n" +
                        "Barry Bee Benson v. the Honey Industry is now in session.\n" +
                        "Mr. Montgomery, you're representing the five food companies collectively?\n" +
                        "A privilege.\n" +
                        "Mr. Benson... you're representing all the bees of the world?\n" +
                        "I'm kidding. Yes, Your Honor, we're ready to proceed.\n" +
                        "Mr. Montgomery, your opening statement, please.\n" +
                        "Ladies and gentlemen of the jury, my grandmother was a simple woman. Born on a farm, she believed it was man's divine right to benefit from the bounty of nature God put before us.\n" +
                        "If we lived in the topsy-turvy world Mr. Benson imagines, just think of what would it mean.\n" +
                        "I would have to negotiate with the silkworm for the elastic in my britches!\n" +
                        "Talking bee!\n" +
                        "How do we know this isn't some sort of holographic motion-picture-capture Hollywood wizardry?\n" +
                        "They could be using laser beams! Robotics! Ventriloquism! Cloning! For all we know, he could be on steroids!\n" +
                        "Mr. Benson?\n" +
                        "Ladies and gentlemen, there's no trickery here. I'm just an ordinary bee. Honey's pretty important to me. It's important to all bees. We invented it! We make it. And we protect it with our lives.\n" +
                        "Unfortunately, there are some people in this room who think they can take it from us 'cause we're the little guys!\n" +
                        "I'm hoping that, after this is all over, you'll see how, by taking our honey, you not only take everything we have but everything we are!\n" +
                        "I wish he'd dress like that all the time. So nice!\n" +
                        "Call your first witness.\n" +
                        "So, Mr. Klauss Vanderhayden of Honey Farms, big company you have.\n" +
                        "I suppose so.\n" +
                        "I see you also own Honeyburton and Honron!\n" +
                        "Yes, they provide beekeepers for our farms.\n" +
                        "Beekeeper. I find that to be a very disturbing term.\n" +
                        "I don't imagine you employ any bee-free-ers, do you?\n" +
                        "No.\n" +
                        "I couldn't hear you.\n" +
                        "No.\n" +
                        "No. Because you don't free bees. You keep bees. Not only that, it seems you thought a bear would be an appropriate image for a jar of honey.\n" +
                        "They're very lovable creatures. Yogi Bear, Fozzie Bear, Build-A-Bear.\n" +
                        "You mean like this?\n" +
                        "Bears kill bees!\n" +
                        "How'd you like his head crashing through your living room?! Biting into your couch! Spitting out your throw pillows! OK, that's enough. Take him away.\n" +
                        "So, Mr. Sting, thank you for being here. Your name intrigues me. Where have I heard it before?\n" +
                        "I was with a band called The Police.\n" +
                        "But you've never been a police officer, have you?\n" +
                        "No, I haven't.\n" +
                        "No, you haven't. And so here we have yet another example of bee culture casually stolen by a human for nothing more than a prance-about stage name.\n" +
                        "Oh, please.\n" +
                        "Have you ever been stung, Mr. Sting? Because I'm feeling a little stung, Sting. Or should I say... Mr. Gordon M. Sumner!\n" +
                        "That's not his real name?! You idiots!\n" +
                        "Mr. Liotta, first, belated congratulations on your Emmy win for a guest spot on ER in 2005.\n" +
                        "Thank you. Thank you.\n" +
                        "I see from your resume that you're devilishly handsome with a churning inner turmoil that's ready to blow.\n" +
                        "I enjoy what I do. Is that a crime?\n" +
                        "Not yet it isn't. But is this what it's come to for you? Exploiting tiny, helpless bees so you don't have to rehearse your part and learn your lines, sir?\n" +
                        "Watch it, Benson! I could blow right now!\n" +
                        "This isn't a goodfella.\n" +
                        "This is a badfella!\n" +
                        "Why doesn't someone just step on this creep, and we can all go home?!\n" +
                        "Order in this court!\n" +
                        "You're all thinking it!\n" +
                        "Order! Order, I say!\n" +
                        "Say it!\n" +
                        "Mr. Liotta, please sit down!\n" +
                        "I think it was awfully nice of that bear to pitch in like that. I think the jury's on our side.\n" +
                        "Are we doing everything right, legally?\n" +
                        "I'm a florist.\n" +
                        "Right. Well, here's to a great team.\n" +
                        "To a great team!\n" +
                        "Well, hello.\n" +
                        "Ken!\n" +
                        "Hello.\n" +
                        "I didn't think you were coming.\n" +
                        "No, I was just late I tried to call, but... the battery.\n" +
                        "I didn't want all this to go to waste,\n" +
                        "so I called Barry. Luckily, he was free.\n" +
                        "Oh, that was lucky.\n" +
                        "There's a little left. I could heat it up.\n" +
                        "Yeah, heat it up, sure, whatever.\n" +
                        "So I hear you're quite a tennis player. I'm not much for the game myself. The ball's a little grabby.\n" +
                        "That's where I usually sit. Right... there.\n" +
                        "Ken, Barry was looking at your resume, and he agreed with me that eating with chopsticks isn't really a special skill.\n" +
                        "You think I don't see what you're doing?\n" +
                        "I know how hard it is to find the right job. We have that in common.\n" +
                        "Do we?\n" +
                        "Bees have 100 percent employment, but we do jobs like taking the crud out.\n" +
                        "That's just what I was thinking about doing.\n" +
                        "Ken, I let Barry borrow your razor for his fuzz. I hope that was all right.\n" +
                        "I'm going to drain the old stinger.\n" +
                        "Yeah, you do that.\n" +
                        "Look at that.\n" +
                        "You know, I've just about had it with your little Mind Games.\n" +
                        "What's that?\n" +
                        "Italian Vogue.\n" +
                        "Mamma mia, that's a lot of pages.\n" +
                        "A lot of ads.\n" +
                        "Remember what Van said, why is your life more valuable than mine?\n" +
                        "Funny, I just can't seem to recall that! I think something stinks in here!\n" +
                        "I love the smell of flowers.\n" +
                        "How do you like the smell of flames?!\n" +
                        "Not as much.\n" +
                        "Water bug! Not taking sides!\n" +
                        "Ken, I'm wearing a Chapstick hat!\n" +
                        "This is pathetic!\n" +
                        "I've got issues!\n" +
                        "Well, well, well, a royal flush!\n" +
                        "You're bluffing.\n" +
                        "Am I?\n" +
                        "Surf's up, dude!\n" +
                        "Poo water!\n" +
                        "That bowl is gnarly. Except for those dirty yellow rings!\n" +
                        "Kenneth! What are you doing?!\n" +
                        "You know, I don't even like honey! I don't eat it!\n" +
                        "We need to talk! He's just a little bee!\n" +
                        "And he happens to be the nicest bee I've met in a long time!\n" +
                        "Long time? What are you talking about?! Are there other bugs in your life?\n" +
                        " No, but there are other things bugging me in life. And you're one of them!\n" +
                        "Fine! Talking bees, no yogurt night...\n" +
                        "My nerves are fried from riding on this emotional roller coaster!\n" +
                        "Goodbye, Ken.\n" +
                        "And for your information, I prefer sugar-free, artificial sweeteners made by man!\n" +
                        "I'm sorry about all that.\n" +
                        "I know it's got an aftertaste! I like it!\n" +
                        "I always felt there was some kind of barrier between Ken and me. I couldn't overcome it.\n" +
                        "Oh, well.\n" +
                        "Are you OK for the trial?\n" +
                        "I believe Mr. Montgomery is about out of ideas.\n" +
                        "We would like to call Mr. Barry Benson Bee to the stand.\n" +
                        "Good idea! You can really see why he's considered one of the best lawyers...\n" +
                        "Yeah.\n" +
                        "Layton, you've gotta weave some magic with this jury, or it's gonna be all over.\n" +
                        "Don't worry. The only thing I have to do to turn this jury around is to remind them of what they don't like about bees.\n" +
                        "You got the tweezers?\n" +
                        "Are you allergic?\n" +
                        "Only to losing, son. Only to losing.\n" +
                        "Mr. Benson Bee, I'll ask you what I think we'd all like to know.\n" +
                        "What exactly is your relationship to that woman?\n" +
                        "We're friends.\n" +
                        "Good friends?\n" +
                        "Yes.\n" +
                        "How good? Do you live together?\n" +
                        "Wait a minute... Are you her little... bedbug?\n" +
                        "I've seen a bee documentary or two. From what I understand, doesn't your queen give birth to all the bee children?\n" +
                        "Yeah, but...\n" +
                        "So those aren't your real parents!\n" +
                        "Oh, Barry...\n" +
                        "Yes, they are!\n" +
                        "Hold me back!\n" +
                        "You're an illegitimate bee, aren't you, Benson?\n" +
                        "He's denouncing bees!\n" +
                        "Don't y'all date your cousins?\n" +
                        "Objection!\n" +
                        "I'm going to pincushion this guy!\n" +
                        "Adam, don't! It's what he wants!\n" +
                        "Oh, I'm hit!! Oh, lordy, I am hit!\n" +
                        "Order! Order!\n" +
                        "The venom! The venom is coursing through my veins! I have been felled by a winged beast of destruction! You see? You can't treat them like equals! They're striped savages! Stinging's the only thing they know! It's their way!\n" +
                        "Adam, stay with me.\n" +
                        "I can't feel my legs.\n" +
                        "What Angel of Mercy will come forward to suck the poison from my heaving buttocks?\n" +
                        "I will have order in this court. Order! Order, please!\n" +
                        "The case of the honeybees versus the human race took a pointed Turn Against the bees yesterday when one of their legal team stung Layton T. Montgomery.\n" +
                        "Hey, buddy.\n" +
                        "Hey.\n" +
                        "Is there much pain?\n" +
                        "Yeah.\n" +
                        "I... I blew the whole case, didn't I?\n" +
                        "It doesn't matter. What matters is\n" +
                        "you're alive. You could have died.\n" +
                        "I'd be better off dead. Look at me.\n" +
                        "They got it from the cafeteria downstairs, in a tuna sandwich. Look, there's a little celery still on it.\n" +
                        "What was it like to sting someone?\n" +
                        "I can't explain it. It was all... All adrenaline and then...and then ecstasy!\n" +
                        "All right.\n" +
                        "You think it was all a trap?\n" +
                        "Of course. I'm sorry. I flew us right into this.\n" +
                        "What were we thinking? Look at us. We're just a couple of bugs in this world.\n" +
                        "What will the humans do to us if they win?\n" +
                        "I don't know.\n" +
                        "I hear they put the roaches in motels. That doesn't sound so bad.\n" +
                        "Adam, they check in, but they don't check out!\n" +
                        "Oh, my.\n" +
                        "Could you get a nurse to close that window?\n" +
                        "Why?\n" +
                        "The smoke.\n" +
                        "Bees don't smoke.\n" +
                        "Right. Bees don't smoke.\n" +
                        "Bees don't smoke!\n" +
                        "But some bees are smoking.\n" +
                        "That's it! That's our case!\n" +
                        "It is? It's not over?\n" +
                        "Get dressed. I've gotta go somewhere.\n" +
                        "Get back to the court and stall. Stall any way you can.\n" +
                        "And assuming you've done step correctly, you're ready for the tub.\n" +
                        "Mr. Flayman.\n" +
                        "Yes? Yes, Your Honor!\n" +
                        "Where is the rest of your team?\n" +
                        "Well, Your Honor, it's interesting. Bees are trained to fly haphazardly, and as a result, we don't make very good time.\n" +
                        "I actually heard a funny story about...\n" +
                        "Your Honor, haven't these ridiculous bugs taken up enough of this court's valuable time? How much longer will we allow these absurd shenanigans to go on?\n" +
                        "They have presented no compelling evidence to support their charges against my clients, who run legitimate businesses.\n" +
                        "I move for a complete dismissal of this entire case!\n" +
                        "Mr. Flayman, I'm afraid I'm going to have to consider Mr. Montgomery's motion.\n" +
                        "But you can't! We have a terrific case.\n" +
                        "Where is your proof?\n" +
                        "Where is the evidence?\n" +
                        "Show me the smoking gun!\n" +
                        "Hold it, Your Honor!\n" +
                        "You want a smoking gun? Here is your smoking gun.\n" +
                        "What is that?\n" +
                        "It's a bee smoker!\n" +
                        "What, this? This harmless little contraption? This couldn't hurt a fly, let alone a bee.\n" +
                        "Look at what has happened to bees who have never been asked, \"Smoking or non?\" Is this what nature intended for us? To be forcibly addicted to smoke machines and man-made wooden slat work camps?\n" +
                        "Living out our lives as honey slaves to the white man?\n" +
                        "What are we gonna do?\n" +
                        "He's playing the species card.\n" +
                        "Ladies and gentlemen, please, free these bees!\n" +
                        "Free the bees! Free the bees! Free the bees! Free the bees! Free the bees!\n" +
                        "The court finds in favor of the bees!\n" +
                        "Vanessa, we won!\n" +
                        "I knew you could do it! High-five!\n" +
                        "Sorry.\n" +
                        "I'm OK! You know what this means?\n" +
                        "All the honey will finally belong to the bees.\n" +
                        "Now we won't have to work so hard all the time.\n" +
                        "This is an unholy perversion of the balance of nature, Benson.\n" +
                        "You'll regret this.\n" +
                        "Barry, how much honey is out there?\n" +
                        "All right. One at a time.\n" +
                        "Barry, who are you wearing?\n" +
                        "My sweater is Ralph Lauren, and I have no pants.\n" +
                        "What if Montgomery's right?\n" +
                        "What do you mean?\n" +
                        "We've been living the bee way a long time, 27 million years.\n" +
                        "Congratulations on your victory. What will you demand as a settlement?\n" +
                        "First, we'll demand a complete shutdown of all bee work camps.\n" +
                        "Then we want back the honey that was ours to begin with, every last drop.\n" +
                        "We demand an end to the glorification of the bear as anything more than a filthy, smelly, bad-breath stink machine.\n" +
                        "We're all aware of what they do in the woods.\n" +
                        "Wait for my signal. Take him out.\n" +
                        "He'll have nauseous for a few hours, then he'll be fine.\n" +
                        "And we will no longer tolerate bee-negative nicknames...\n" +
                        "But it's just a prance-about stage name!\n" +
                        "...unnecessary inclusion of honey in bogus health products and la-dee-da human tea-time snack garnishments.\n" +
                        "Can't breathe.\n" +
                        "Bring it in, boys!\n" +
                        "Hold it right there! Good.\n" +
                        "Tap it.\n" +
                        "Mr. Buzzwell, we just passed three cups and there's gallons more coming!\n" +
                        "I think we need to shut down!\n" +
                        "Shut down? We've never shut down.\n" +
                        "Shut down honey production!\n" +
                        "Stop making honey!\n" +
                        "Turn your key, sir!\n" +
                        "What do we do now?\n" +
                        "Cannonball!\n" +
                        "We're shutting honey production!\n" +
                        "Mission abort.\n" +
                        "Aborting pollination and nectar detail.\n" +
                        "Returning to base.\n" +
                        "Adam, you wouldn't believe how much honey was out there.\n" +
                        "Oh, yeah?\n" +
                        "What's going on? Where is everybody?\n" +
                        "Are they out celebrating?\n" +
                        "They're home.\n" +
                        "They don't know what to do. Laying out, sleeping in.\n" +
                        "I heard your Uncle Carl was on his way to San Antonio with a cricket.\n" +
                        "At least we got our honey back.\n" +
                        "Sometimes I think, so what if humans liked our honey? Who wouldn't?\n" +
                        "It's the greatest thing in the world! I was excited to be part of making it.\n" +
                        "This was my new desk. This was my new job. I wanted to do it really well. And now...\n" +
                        "Now I can't.\n" +
                        "I don't understand why they're not happy.\n" +
                        "I thought their lives would be better!\n" +
                        "They're doing nothing. It's amazing.\n" +
                        "Honey really changes people.\n" +
                        "You don't have any idea what's going on, do you?\n" +
                        "What did you want to show me?\n" +
                        "This.\n" +
                        "What happened here?\n" +
                        "That is not the half of it.\n" +
                        "Oh, no. Oh, my.\n" +
                        "They're all wilting.\n" +
                        "Doesn't look very good, does it?\n" +
                        "No.\n" +
                        "And whose fault do you think that is?\n" +
                        "You know, I'm gonna guess bees.\n" +
                        "Bees?\n" +
                        "Specifically, me.\n" +
                        "I didn't think bees not needing to make honey would affect all these things.\n" +
                        "It's not just flowers. Fruits, vegetables, they all need bees.\n" +
                        "That's our whole SAT test right there.\n" +
                        "Take away produce, that affects the entire animal kingdom.\n" +
                        "And then, of course...\n" +
                        "The human species?\n" +
                        "So if there's no more pollination, it could all just go south here, couldn't it?\n" +
                        "I know this is also partly my fault.\n" +
                        "How about a suicide pact?\n" +
                        "How do we do it?\n" +
                        "I'll sting you, you step on me.\n" +
                        "That just kills you twice.\n" +
                        "Right, right.\n" +
                        "Listen, Barry... sorry, but I gotta get going.\n" +
                        "I had to open my mouth and talk.\n" +
                        "Vanessa?\n" +
                        "Vanessa? Why are you leaving?\n" +
                        "Where are you going?\n" +
                        "To the final Tournament of Roses parade in Pasadena.\n" +
                        "They've moved it to this weekend because all the flowers are dying.\n" +
                        "It's the Last Chance I'll ever have to see it.\n" +
                        "Vanessa, I just wanna say I'm sorry.\n" +
                        "I never meant it to turn out like this.\n" +
                        "I know. Me neither.\n" +
                        "Tournament of Roses.\n" +
                        "Roses can't do sports.\n" +
                        "Wait a minute. Roses. Roses?\n" +
                        "Roses!\n" +
                        "Vanessa!\n" +
                        "Roses?!\n" +
                        "Barry?\n" +
                        "Roses are flowers!\n" +
                        "Yes, they are.\n" +
                        "Flowers, bees, pollen!\n" +
                        "I know.\n" +
                        "That's why this is the last parade.\n" +
                        "Maybe not.\n" +
                        "Could you ask him to slow down?\n" +
                        "Could you slow down?\n" +
                        "Barry!\n" +
                        "OK, I made a huge mistake.\n" +
                        "This is a total disaster, all my fault.\n" +
                        "Yes, it kind of is.\n" +
                        "I've ruined the planet. I wanted to help you with the flower shop. I've made it worse.\n" +
                        "Actually, it's completely closed down.\n" +
                        "I thought maybe you were remodeling.\n" +
                        "But I have another idea, and it's greater than my previous ideas combined.\n" +
                        "I don't want to hear it!\n" +
                        "All right, they have the roses, the roses have the pollen.\n" +
                        "I know every bee, plant and flower bud in this park.\n" +
                        "All we gotta do is get what they've got back here with what we've got.\n" +
                        "Bees.\n" +
                        "Park.\n" +
                        "Pollen!\n" +
                        "Flowers.\n" +
                        "Repollination!\n" +
                        "Across the nation!\n" +
                        "Tournament of Roses, Pasadena, California.\n" +
                        "They've got nothing but flowers, floats and cotton candy.\n" +
                        "Security will be tight.\n" +
                        "I have an idea.\n" +
                        "Vanessa Bloome, FTD.\n" +
                        "Official floral business. It's real.\n" +
                        "Sorry, ma'am. Nice brooch.\n" +
                        "Thank you. It was a gift.\n" +
                        "Once inside, we just pick the right float.\n" +
                        "How about The Princess and the Pea?\n" +
                        "I could be the princess, and you could be the pea!\n" +
                        "Yes, I got it.\n" +
                        "Where should I sit?\n" +
                        "What are you?\n" +
                        "I believe I'm the pea.\n" +
                        "The pea?\n" +
                        "It goes under the mattresses.\n" +
                        "Not in this fairy tale, sweetheart.\n" +
                        "I'm getting the marshal.\n" +
                        "You do that! This whole parade is a fiasco!\n" +
                        "Let's see what this baby'll do.\n" +
                        "Hey, what are you doing?!\n" +
                        "Then all we do is blend in with traffic... without arousing suspicion.\n" +
                        "Once at the airport, there's no stopping us.\n" +
                        "Stop! Security.\n" +
                        "You and your insect pack your float?\n" +
                        "Yes.\n" +
                        "Has it been in your possession the entire time?\n" +
                        "Would you remove your shoes?\n" +
                        "Remove your stinger.\n" +
                        "It's part of me.\n" +
                        "I know. Just having some fun.\n" +
                        "Enjoy your flight.\n" +
                        "Then if we're lucky, we'll have just enough pollen to do the job.\n" +
                        "Can you believe how lucky we are? We have just enough pollen to do the job!\n" +
                        "I think this is gonna work.\n" +
                        "It's got to work.\n" +
                        "Attention, passengers, this is Captain Scott. We have a bit of bad weather in New York. It looks like we'll experience a couple hours delay.\n" +
                        "Barry, these are cut flowers with no water. They'll never make it.\n" +
                        "I gotta get up there and talk to them.\n" +
                        "Be careful.\n" +
                        "Can I get help with the Sky Mall magazine? I'd like to order the talking inflatable nose and ear hair trimmer.\n" +
                        "Captain, I'm in a real situation.\n" +
                        "What'd you say, Hal?\n" +
                        "Nothing.\n" +
                        "Bee!\n" +
                        "Don't freak out! My entire species...\n" +
                        "What are you doing?\n" +
                        "Wait a minute! I'm an attorney!\n" +
                        "Who's an attorney?\n" +
                        "Don't move.\n" +
                        "Oh, Barry.\n" +
                        "Good afternoon, passengers. This is your captain. Would a Miss Vanessa Bloome in 24B please report to the cockpit? And please hurry!\n" +
                        "What happened here?\n" +
                        "There was a DustBuster, a toupee, a life raft exploded.\n" +
                        "One's bald, one's in a boat, they're both unconscious!\n" +
                        "Is that another bee joke?\n" +
                        "No!\n" +
                        "No one's flying the plane!\n" +
                        "This is JFK control tower, Flight 356. What's your status?\n" +
                        "This is Vanessa Bloome. I'm a florist from New York.\n" +
                        "Where's the pilot?\n" +
                        "He's unconscious, and so is the copilot.\n" +
                        "Not good. Does anyone onboard have flight experience?\n" +
                        "As a matter of fact, there is.\n" +
                        "Who's that?\n" +
                        "Barry Benson.\n" +
                        "From the honey trial?! Oh, great.\n" +
                        "Vanessa, this is nothing more than a big metal bee.\n" +
                        "It's got giant wings, huge engines.\n" +
                        "I can't fly a plane.\n" +
                        "Why not? Isn't John Travolta a pilot?\n" +
                        "Yes.\n" +
                        "How hard could it be?\n" +
                        "Wait, Barry!\n" +
                        "We're headed into some lightning.\n" +
                        "This is Bob Bumble. We have some late-breaking news from JFK Airport, where a suspenseful scene is developing.\n" +
                        "Barry Benson, fresh from his legal victory...\n" +
                        "That's Barry!\n" +
                        "...is attempting to land a plane, loaded with people, flowers and an incapacitated flight crew.\n" +
                        "Flowers?!\n" +
                        "We have a storm in the area and two individuals at the controls with absolutely no flight experience.\n" +
                        "Just a minute. There's a bee on that plane.\n" +
                        "I'm quite familiar with Mr. Benson and his no-account compadres.\n" +
                        "They've done enough damage.\n" +
                        "But isn't he your only hope?\n" +
                        "Technically, a bee shouldn't be able to fly at all.\n" +
                        "Their wings are too small... Haven't we heard this a million times?\n" +
                        "\"The surface area of the wings and body mass make no sense.\"\n" +
                        "Get this on the air!\n" +
                        "Got it.\n" +
                        "Stand by.\n" +
                        "We're going live.\n" +
                        "The way we work may be a mystery to you. Making honey takes a lot of bees doing a lot of small jobs.\n" +
                        "But let me tell you about a small job. If you do it well, it makes a big difference.\n" +
                        "More than we realized. To us, to everyone.\n" +
                        "That's why I want to get bees back to working together. That's the bee way! We're not made of Jell-O.\n" +
                        "We get behind a fellow.\n" +
                        "Black and yellow!\n" +
                        "Hello!\n" +
                        "Left, right, down, hover.\n" +
                        "Hover?\n" +
                        "Forget hover.\n" +
                        "This isn't so hard.\n" +
                        "Beep-beep! Beep-beep!\n" +
                        "Barry, what happened?!\n" +
                        "Wait, I think we were on autopilot the whole time.\n" +
                        "That may have been helping me.\n" +
                        "And now we're not!\n" +
                        "So it turns out I cannot fly a plane.\n" +
                        "All of you, let's get behind this fellow! Move it out!\n" +
                        "Move out!\n" +
                        "Our only chance is if I do what I'd do, you copy me with the wings of the plane!\n" +
                        "Don't have to yell.\n" +
                        "I'm not yelling! We're in a lot of trouble.\n" +
                        "It's very hard to concentrate with that panicky tone in your voice!\n" +
                        "It's not a tone. I'm panicking!\n" +
                        "I can't do this!\n" +
                        "Vanessa, pull yourself together. You have to snap out of it!\n" +
                        "You snap out of it.\n" +
                        "You snap out of it.\n" +
                        "You snap out of it!\n" +
                        "You snap out of it!\n" +
                        "You snap out of it!\n" +
                        "You snap out of it!\n" +
                        "You snap out of it!\n" +
                        "You snap out of it!\n" +
                        "Hold it!\n" +
                        "Why? Come on, it's my turn.\n" +
                        "How is the plane flying?\n" +
                        "I don't know.\n" +
                        "Hello?\n" +
                        "Benson, got any flowers for a happy occasion in there?\n" +
                        "The Pollen Jocks!\n" +
                        "They do get behind a fellow.\n" +
                        "Black and yellow.\n" +
                        "Hello.\n" +
                        "All right, let's drop this tin can on the blacktop.\n" +
                        "Where? I can't see anything. Can you?\n" +
                        "No, nothing. It's all cloudy.\n" +
                        "Come on. You got to think bee, Barry.\n" +
                        "Thinking bee.\n" +
                        "Thinking bee.\n" +
                        "Thinking bee!\n" +
                        "Thinking bee! Thinking bee!\n" +
                        "Wait a minute. I think I'm feeling something.\n" +
                        "What?\n" +
                        "I don't know. It's strong, pulling me.\n" +
                        "Like a 27-million-year-old instinct.\n" +
                        "Bring the nose down.\n" +
                        "Thinking bee!\n" +
                        "Thinking bee! Thinking bee!\n" +
                        "What in the world is on the tarmac?\n" +
                        "Get some lights on that!\n" +
                        "Thinking bee!\n" +
                        "Thinking bee! Thinking bee!\n" +
                        "Vanessa, aim for the flower.\n" +
                        "OK.\n" +
                        "Cut the engines. We're going in on bee power. Ready, boys?\n" +
                        "Affirmative!\n" +
                        "Good. Good. Easy, now. That's it.\n" +
                        "Land on that flower!\n" +
                        "Ready? Full reverse!\n" +
                        "Spin it around!\n" +
                        "Not that flower! The other one!\n" +
                        "Which one?\n" +
                        "That flower.\n" +
                        "I'm aiming at the flower!\n" +
                        "That's a fat guy in a flowered shirt.\n" +
                        "I mean the giant pulsating flower made of millions of bees!\n" +
                        "Pull forward. Nose down. Tail up.\n" +
                        "Rotate around it.\n" +
                        "This is insane, Barry!\n" +
                        "This's the only way I know how to fly.\n" +
                        "Am I koo-koo-kachoo, or is this plane flying in an insect-like pattern?\n" +
                        "Get your nose in there. Don't be afraid. Smell it. Full reverse!\n" +
                        "Just drop it. Be a part of it.\n" +
                        "Aim for the center!\n" +
                        "Now drop it in! Drop it in, woman!\n" +
                        "Come on, already.\n" +
                        "Barry, we did it! You taught me how to fly!\n" +
                        "Yes. No high-five!\n" +
                        "Right.\n" +
                        "Barry, it worked!\n" +
                        "Did you see the giant flower?\n" +
                        "What giant flower? Where? Of course\n" +
                        "I saw the flower! That was genius!\n" +
                        "Thank you.\n" +
                        "But we're not done yet.\n" +
                        "Listen, everyone!\n" +
                        "This runway is covered with the last pollen from the last flowers available anywhere on Earth.\n" +
                        "That means this is our Last Chance. We're the only ones who make honey, pollinate flowers and dress like this.\n" +
                        "If we're gonna survive as a species, this is our moment! What do you say?\n" +
                        "Are we going to be bees, or just Museum of Natural History keychains?\n" +
                        "We're bees!\n" +
                        "Keychain!\n" +
                        "Then follow me! Except Keychain.\n" +
                        "Hold on, Barry. Here. You've earned this.\n" +
                        "Yeah!\n" +
                        "I'm a Pollen Jock! And it's a perfect fit. All I gotta do are the sleeves.\n" +
                        "Oh, yeah.\n" +
                        "That's our Barry.\n" +
                        "Mom! The bees are back!\n" +
                        "If anybody needs to make a call, now's the time. I got a feeling we'll be working late tonight!\n" +
                        "Here's your change. Have a great afternoon! Can I help who's next?\n" +
                        "Would you like some honey with that?\n" +
                        "It is bee-approved. Don't forget these.\n" +
                        "Milk, cream, cheese, it's all me.  And I don't see a nickel!\n" +
                        "Sometimes I just feel like a piece of meat!\n" +
                        "I had no idea.\n" +
                        "Barry, I'm sorry.\n" +
                        "Have you got a moment?\n" +
                        "Would you excuse me?\n" +
                        "My mosquito associate will help you.\n" +
                        "Sorry I'm late.\n" +
                        "He's a lawyer too?\n" +
                        "I was already a blood-sucking parasite. All I needed was a briefcase.\n" +
                        "Have a great afternoon!\n" +
                        "Barry, I just got this huge tulip order, and I can't get them anywhere.\n" +
                        "No problem, Vannie. Just leave it to me.\n" +
                        "You're a lifesaver, Barry. Can I help who's next?\n" +
                        "All right, scramble, jocks! It's time to fly.\n" +
                        "Thank you, Barry!\n" +
                        "That bee is living my life!\n" +
                        "Let it go, Kenny.\n" +
                        "When will this nightmare end?!\n" +
                        "Let it all go.\n" +
                        "Beautiful day to fly.\n" +
                        "Sure is.\n" +
                        "Between you and me,\n" +
                        "I was dying to get out of that office.\n" +
                        "You have got to start thinking bee, my friend.\n" +
                        "Thinking bee!\n" +
                        "Me?\n" +
                        "Hold it. Let's just stop for a second. Hold it.\n" +
                        "I'm sorry. I'm sorry, everyone. Can we stop here?\n" +
                        "I'm not making a major life decision during a production number!\n" +
                        "All right. Take ten, everybody. Wrap it up, guys.\n" +
                        "I had virtually no rehearsal for that.";

        return script;
    }

    @Command(name = "breakpoint", description = "Used for debugging")
    public void breakpointCommand(Player player) {
        player.sendMessage(ChatColor.RED + "You sussy baka");
    }
}
