import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.analysis.*; 
import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Game extends PApplet {

//Horse Betting Simulator
//J. Chi

//Thanks this website to teach me how to show animated image
//http://www.science.smith.edu/dftwiki/index.php/Processing_Tutorial_--_Showing_Animated_Gifs
//Thanks to https://ezgif.com/split to help me cut the images into pieces

//using library "Minim"
//thanks to the developers of this library


Minim minim;


//Positions of the horse
float position1 = 0;
float position2 = 0;
float position3 = 0;
float position4 = 0;
float position5 = 0;
float position6 = 0;

//start money
int money = 500;

//to store which horse picked
int pick = 0;

//score count
int win = 0;
int lose = 0;

//conditions
boolean start,start1,restart,quit,ingame;
boolean nowin;

//Picture of horse
PImage []horse = new PImage[15];

//Pictures of audience
PImage cheering1, cheering2, cheering3, calmdown;

//sound effects
AudioPlayer beforegame;
AudioPlayer duringgame;

public void setup()
{
  //the screen is a little too large
  size(1500,850,P2D);
  
  minim = new Minim(this);
  
  //to load image
  //https://www.cleanpng.com/png-antelope-walk-cycle-goat-animal-cattle-sprite-1251591/preview.html
  horse[0] = loadImage("tile000.png");
  horse[1] = loadImage("tile001.png");
  horse[2] = loadImage("tile002.png");
  horse[3] = loadImage("tile003.png");
  horse[4] = loadImage("tile004.png");
  horse[5] = loadImage("tile005.png");
  horse[6] = loadImage("tile006.png");
  horse[7] = loadImage("tile007.png");
  horse[8] = loadImage("tile008.png");
  horse[9] = loadImage("tile009.png");
  horse[10] = loadImage("tile010.png");
  horse[11] = loadImage("tile011.png");
  horse[12] = loadImage("tile012.png");
  horse[13] = loadImage("tile013.png");
  horse[14] = loadImage("tile014.png");
  
  //https://www.cleanpng.com/png-crowd-cheering-clip-art-shadow-712090/
  //https://www.cleanpng.com/png-silhouette-shadow-person-clip-art-peoples-1104257/
  //https://www.cleanpng.com/png-silhouette-person-1862541/
  //https://www.cleanpng.com/png-applause-image-clip-art-portable-network-graphics-7153470/
  cheering1 = loadImage("cheering1.png");
  cheering2 = loadImage("cheering2.png");
  cheering3 = loadImage("cheering3.png");
  calmdown = loadImage("calmdown.png");
  
  //to load sounds
  //http://soundbible.com/1724-Large-Stadium.html
  //http://soundbible.com/1265-Shopping-Mall-Ambiance.html
  beforegame = minim.loadFile("prepare.wav");
  duringgame = minim.loadFile("duringgame.wav");
  
  //to always have some background sounds
  beforegame.loop();
  
  //to resize the picture
  horse[0].resize(100,100);
  horse[1].resize(100,100);
  horse[2].resize(100,100);
  horse[3].resize(100,100);
  horse[4].resize(100,100);
  horse[5].resize(100,100);
  horse[6].resize(100,100);
  horse[7].resize(100,100);
  horse[8].resize(100,100);
  horse[9].resize(100,100);
  horse[10].resize(100,100);
  horse[11].resize(100,100);
  horse[12].resize(100,100);
  horse[13].resize(100,100);
  horse[14].resize(100,100);
  
  calmdown.resize(300,100);
  cheering1.resize(300,100);
  cheering2.resize(400,200);
  cheering3.resize(300,100);
  
  //to adjust the movement of horses' legs
  frameRate(20);
  
  //to add scores
  if(pick==1 && position1 > position2 && position1 > position3 && position1 > position4 && position1 > position5 && position1 > position6 && position1+50>1400)
  {
    win = win+1;
    money = money + 600;
  }
  else if(pick==2 && position2 > position1 && position2 > position3 && position2 > position4 && position2 > position5 && position2 > position6 && position2+50>1400)
  {
    win = win+1;
    money = money + 600;
  }
  else if(pick==3 && position3 > position2 && position3 > position1 && position3 > position4 && position3 > position5 && position3 > position6 && position3+50>1400)
  {
    win = win+1;
    money = money + 600;
  }
  else if(pick==4 && position4 > position2 && position4 > position3 && position4 > position1 && position4 > position5 && position4 > position6 && position4+50>1400)
  {
    win = win+1;
    money = money + 600;
  }
  else if(pick==5 && position5 > position2 && position5 > position3 && position5 > position4 && position5 > position1 && position5 > position6 && position5+50>1400)
  {
    money = money + 600;
  }
  else if(pick==6 && position6 > position2 && position6 > position3 && position6 > position4 && position6 > position5 && position6 > position1 && position6+50>1400)
  {
    money = money + 600;
  }
  else if(nowin)
  {
    lose = lose+1;
    nowin = false;
  }

}

public void draw()
{
   background(255,255,255);
   
   //the environment
   fill(0);
   rect(0,200,1500,10);
   rect(0,800,1500,10);
   fill(255,0,0);
   rect(1400,210,5,590);
   fill(255,255,0);
   rect(0,300,1400,2);
   rect(0,400,1400,2);
   rect(0,500,1400,2);
   rect(0,600,1400,2);
   rect(0,700,1400,2);
   fill(0);
   rect(600,30,300,100);
   rect(600,130,7,50);
   rect(893,130,7,50);
   
     
     
   //to play the cheering sound
   //to put some audience
   if(ingame == true)
   {
     duringgame.play();
   }
   else
   {
     duringgame.pause();
     image(calmdown, 0,100);
     image(calmdown, 250,100);
     image(calmdown, 930,100);
     image(calmdown, 1200,100);
   }

 
   //the position of the horses (position = 0 at the beginning)
   image(horse[frameCount%15],position1,200);
   image(horse[frameCount%15],position2,300);
   image(horse[frameCount%15],position3,400);
   image(horse[frameCount%15],position4,500);
   image(horse[frameCount%15],position5,600);
   image(horse[frameCount%15],position6,700);
   
   if(pick==0)
   {
      fill(0);
      textSize(38);
      text("1", 150,250);
      text("2", 150,350);
      text("3", 150,450);
      text("4", 150,550);
      text("5", 150,650);
      text("6", 150,750);
   }
  
    //the number will turn green if the horse if picked
    if(pick==1)
    {
      textSize(38);
      fill(0,255,0);
      text("1", 150,250);
      fill(0);
      text("2", 150,350);
      text("3", 150,450);
      text("4", 150,550);
      text("5", 150,650);
      text("6", 150,750);
    }
    if(pick==2)
    {
      textSize(38);
      fill(0);
      text("1", 150,250);
      fill(0,255,0);
      text("2", 150,350);
      fill(0);
      text("3", 150,450);
      text("4", 150,550);
      text("5", 150,650);
      text("6", 150,750);
    }
    if(pick==3)
    {
      textSize(38);
      fill(0);
      text("1", 150,250);
      text("2", 150,350);
      fill(0,255,0);
      text("3", 150,450);
      fill(0);
      text("4", 150,550);
      text("5", 150,650);
      text("6", 150,750);
    }
    if(pick==4)
    {
      textSize(38);
      fill(0);
      text("1", 150,250);
      text("2", 150,350);
      text("3", 150,450);
      fill(0,255,0);
      text("4", 150,550);
      fill(0);
      text("5", 150,650);
      text("6", 150,750);
    }
    if(pick==5)
    {
      textSize(38);
      fill(0);
      text("1", 150,250);
      text("2", 150,350);
      text("3", 150,450);
      text("4", 150,550);
      fill(0,255,0);
      text("5", 150,650);
      fill(0);
      text("6", 150,750);
    }
    if(pick==6)
    {
      textSize(38);
      fill(0);
      text("1", 150,250);
      text("2", 150,350);
      text("3", 150,450);
      text("4", 150,550);
      text("5", 150,650);
      fill(0,255,0);
      text("6", 150,750);
    }
  
     //to remind player to choose a horse on the screen
     fill(255);
     textSize(20);
     text("click on horse to choose", 620,90);
     
     //game start
     if (start && pick >0)
     {
         game();       
     }
     
     //big red sentence to remind player to choose a horse
     if (start1 && pick == 0)
     {
       textSize(50);
       fill(255,0,0);
       text("YOU HAVE TO CHOOSE A HORSE FIRST", 250,400);
     }
     else if(start == false)
     {
       textSize(40);
       fill(255);
       rect(500,410, 350, 50);
       fill(0);
       text("HERE TO START", 510, 445);

     }
     
     //to show score on the screen
     textSize(30);
     fill(0,0,255);
     text("WIN: " + win, 50,840);
     text("LOSE: " + lose, 350,840);
     text("MONEY: " + money, 650, 840);
}

public void game()
{
  background(255,255,255);
  
  //environment
  fill(0);
  rect(0,200,1500,10);
  rect(0,800,1500,10);
  fill(255,0,0);
  rect(1400,210,5,590);
  fill(255,255,0);
  rect(0,300,1400,2);
  rect(0,400,1400,2);
  rect(0,500,1400,2);
  rect(0,600,1400,2);
  rect(0,700,1400,2);
  fill(0);
  rect(600,30,300,100);
  rect(600,130,7,50);
  rect(893,130,7,50);
  
  //the horse run with a random speed before any of them reaches the final line
  //and the aidience will start cheering
  if(position1+50<1400 && position2+50<1400 && position3+50<1400 && position4+50<1400 && position5+50<1400 && position6+50<1400)
  {
    position1 = position1+random(0,10);
    position2 = position2+random(0,10);
    position3 = position3+random(0,10);
    position4 = position4+random(0,10);
    position5 = position5+random(0,10);
    position6 = position6+random(0,10);
    ingame = true;
    image(cheering1, 0,100);
    image(cheering2, 250,0);
    image(cheering3,930,100);
    image(cheering2,1150,0);
  }
  
  //if any of the horses reaches the final line, they starts to slow down to the same speed
  //and to put the audience back to calm
  if(position1+50>1400 || position2+50>1400 || position3+50>1400 || position4+50>1400 || position5+50>1400 || position6+50>1400)
  {
    position1 = position1+3;
    position2 = position2+3;
    position3 = position3+3;
    position4 = position4+3;
    position5 = position5+3;
    position6 = position6+3;
    ingame = false;
    image(calmdown, 0,100);
    image(calmdown, 250,100);
    image(calmdown, 930,100);
    image(calmdown, 1200,100);
  }
  
  
  
  if(pick==0)
  {
    fill(0);
    textSize(38);
    text("1", 150,250);
    text("2", 150,350);
    text("3", 150,450);
    text("4", 150,550);
    text("5", 150,650);
    text("6", 150,750);
  }
  
  //the number will turn green when the horse is picked
  if(pick==1)
  {
    textSize(38);
    fill(0,255,0);
    text("1", 150,250);
    fill(0);
    text("2", 150,350);
    text("3", 150,450);
    text("4", 150,550);
    text("5", 150,650);
    text("6", 150,750);
  }
  
  if(pick==2)
  {
    textSize(38);
    fill(0);
    text("1", 150,250);
    fill(0,255,0);
    text("2", 150,350);
    fill(0);
    text("3", 150,450);
    text("4", 150,550);
    text("5", 150,650);
    text("6", 150,750);
  }
  
  if(pick==3)
  {
    textSize(38);
    fill(0);
    text("1", 150,250);
    text("2", 150,350);
    fill(0,255,0);
    text("3", 150,450);
    fill(0);
    text("4", 150,550);
    text("5", 150,650);
    text("6", 150,750);
  }
  
  if(pick==4)
  {
    textSize(38);
    fill(0);
    text("1", 150,250);
    text("2", 150,350);
    text("3", 150,450);
    fill(0,255,0);
    text("4", 150,550);
    fill(0);
    text("5", 150,650);
    text("6", 150,750);
  }
  
  if(pick==5)
  {
    textSize(38);
    fill(0);
    text("1", 150,250);
    text("2", 150,350);
    text("3", 150,450);
    text("4", 150,550);
    fill(0,255,0);
    text("5", 150,650);
    fill(0);
    text("6", 150,750);
  }
  
  if(pick==6)
  {
    textSize(38);
    fill(0);
    text("1", 150,250);
    text("2", 150,350);
    text("3", 150,450);
    text("4", 150,550);
    text("5", 150,650);
    fill(0,255,0);
    text("6", 150,750);
  }
  

  
  //the position of the horses
  image(horse[frameCount%15],position1,200);
  image(horse[frameCount%15],position2,300);
  image(horse[frameCount%15],position3,400);
  image(horse[frameCount%15],position4,500);
  image(horse[frameCount%15],position5,600);
  image(horse[frameCount%15],position6,700);
  
  //to show which horse is leading
  if(position1 > position2 && position1 > position3 && position1 > position4 && position1 > position5 && position1 > position6 && position1+50<1400)
  {
    fill(255);
    textSize(25);
    text("horse 1 is leading", 630,90);
  }
  if(position2 > position1 && position2 > position3 && position2 > position4 && position2 > position5 && position2 > position6 && position2+50<1400)
  {
    fill(255);
    textSize(25);
    text("horse 2 is leading", 630,90);
  }
  if(position3 > position2 && position3 > position1 && position3 > position4 && position3 > position5 && position3 > position6 && position3+50<1400)
  {
    fill(255);
    textSize(25);
    text("horse 3 is leading", 630,90);
  }
  if(position4 > position2 && position4 > position3 && position4 > position1 && position4 > position5 && position4 > position6 && position4+50<1400)
  {
    fill(255);
    textSize(25);
    text("horse 4 is leading", 630,90);
  }
  if(position5 > position2 && position5 > position3 && position5 > position4 && position5 > position1 && position5 > position6 && position5+50<1400)
  {
    fill(255);
    textSize(25);
    text("horse 5 is leading", 630,90);
  }
  if(position6 > position2 && position6 > position3 && position6 > position4 && position6 > position5 && position6 > position1 && position6+50<1400)
  {
    fill(255);
    textSize(25);
    text("horse 6 is leading", 630,90);
  }
  
  //to show which horse won
  if(position1 > position2 && position1 > position3 && position1 > position4 && position1 > position5 && position1 > position6 && position1+50>1400)
  {
    fill(255);
    textSize(25);
    text("horse 1 won",630,90);
    gameover();
  }  
  if(position2 > position1 && position2 > position3 && position2 > position4 && position2 > position5 && position2 > position6 && position2+50>1400)
  {
    fill(255);
    textSize(25);
    text("horse 2 won",630,90);
    gameover();
  }  
  if(position3 > position2 && position3 > position1 && position3 > position4 && position3 > position5 && position3 > position6 && position3+50>1400)
  {
    fill(255);
    textSize(25);
    text("horse 3 won",630,90);
    gameover();
  }  
  if(position4 > position2 && position4 > position3 && position4 > position1 && position4 > position5 && position4 > position6 && position4+50>1400)
  {
    fill(255);
    textSize(25);
    text("horse 4 won",630,90);
    gameover();
  }  
  if(position5 > position2 && position5 > position3 && position5 > position4 && position5 > position1 && position5 > position6 && position5+50>1400)
  {
    fill(255);
    textSize(25);
    text("horse 5 won",630,90);
    gameover();
  }  
  if(position6 > position2 && position6 > position3 && position6 > position4 && position6 > position5 && position6 > position1 && position6+50>1400)
  {
    fill(255);
    textSize(25);
    text("horse 6 won",630,90);
    gameover();
  }  
  
}

public void gameover()
{
  textSize(50);
  fill(255,120,80);
  text("GAME OVER",600,400);
  
  //to show if the player wins or loses
  if(pick==1 && position1 > position2 && position1 > position3 && position1 > position4 && position1 > position5 && position1 > position6 && position1+50>1400)
  {
    text("YOU WIN",600,450);
  }
  else if(pick==2 && position2 > position1 && position2 > position3 && position2 > position4 && position2 > position5 && position2 > position6 && position2+50>1400)
  {
    text("YOU WIN",600,450);
  }
  else if(pick==3 && position3 > position2 && position3 > position1 && position3 > position4 && position3 > position5 && position3 > position6 && position3+50>1400)
  {
    text("YOU WIN",600,450);
  }
  else if(pick==4 && position4 > position2 && position4 > position3 && position4 > position1 && position4 > position5 && position4 > position6 && position4+50>1400)
  {
    text("YOU WIN",600,450);
  }
  else if(pick==5 && position5 > position2 && position5 > position3 && position5 > position4 && position5 > position1 && position5 > position6 && position5+50>1400)
  {
    text("YOU WIN",600,450);
  }
  else if(pick==6 && position6 > position2 && position6 > position3 && position6 > position4 && position6 > position5 && position6 > position1 && position6+50>1400)
  {
    text("YOU WIN",600,450);
  }
  else
  {
    text("YOU LOSE",600,450);
    nowin = true;
  }
  
  //to restart
  if(restart)
  {
    setup();
    position1=0;
    position2=0;
    position3=0;
    position4=0;
    position5=0;
    position6=0;
    pick=0;
    start = false;
    start1 = false;
    restart = false;
    
    
  }
  else
  {
    //to show the options of "ok" and "quit"
    //click "ok" to restart
    //click "quit" to quit the game
    textSize(50);
    fill(255);
    rect(600,520,300,70);
    fill(0);
    text("OK", 620,565);
    
    textSize(50);
    fill(255);
    rect(600,620,300,70);
    fill(0);
    text("QUIT", 620,665);
    
    //quit
    if(quit)
    {
      exit();
    }
  }
}
  
public void mousePressed()
{
  //to press start button
  if (mouseX > 501 && mouseX < 849 && mouseY > 411 && mouseY < 459 && pick > 0)
  {
      start = true;
      money = money - 100;
  }

  //to press start button but no horse picked
  if (mouseX > 501 && mouseX < 849 && mouseY > 411 && mouseY < 459 && pick == 0)
    start1 = true;
  
  //to pick horses
  if (mouseX > 1 && mouseX < 99 && mouseY >201 && mouseY <299 && start == false)
  {
    pick = 1;
  }
  if (mouseX > 1 && mouseX < 99 && mouseY >301 && mouseY <399 && start == false)
  {
    pick = 2;
  }
  if (mouseX > 1 && mouseX < 99 && mouseY >401 && mouseY <499 && start == false)
  {
    pick = 3;
  }
  if (mouseX > 1 && mouseX < 99 && mouseY >501 && mouseY <599 && start == false)
  {
    pick = 4;
  }
  if (mouseX > 1 && mouseX < 99 && mouseY >601 && mouseY <699 && start == false)
  {
    pick = 5;
  }
  if (mouseX > 1 && mouseX < 99 && mouseY >701 && mouseY <799 && start == false)
  {
    pick = 6;
  }
  
  //to press restart button
  if (mouseX > 601 && mouseX < 899 && mouseY > 519 && mouseY < 589 && (position1+50>1400 || position2+50>1400 || position3+50>1400 || position4+50>1400 || position5+50>1400 || position6+50>1400))
    restart = true;
    
  //to press quit button
  if (mouseX > 601 && mouseX < 899 && mouseY > 619 && mouseY < 689 && (position1+50>1400 || position2+50>1400 || position3+50>1400 || position4+50>1400 || position5+50>1400 || position6+50>1400))
    quit = true;
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
