# Human Benchmark
---

## Human Benchmark Overview

**Author: Shane Bramley**

A rebuild of the Human Benchmark application on the Human benchmark website it includes multiple games including:
- **Aim Trainer**   - This game will test the speed of the user by clicking 30 targets as quickly as possible.
- **Reaction Time** - This game will test the reaction time of the user by changing images then the use clicks as quickly as possible
- **Chimp Test**    - This game will test the users memory of the numbers locations count 1-n
- **Typing**        - This game will test the speed of the users typing by giving a paragraph then the user has to type it out
- **Visual Memory** - This game will test the visual memory of the user by displaying a grid of boxes and having them memorize which boxes to select
- **Verbal Memory** - This game will test the user on memory of which words have been show and which have not been shown
- **Hearing**       - This game will test the users hearing to see when they can hear the highest frequency
- **Number Memory** - This game will display out the number(s) to the user and have them memorize it then have them enter the number in a box

---

## Functionality

- Starting Application
  - Home page
    - Select a mini game you would to play
    - Each mini game has its own list of instructions
- In mini game
  - Home button will take you to the home page of the application
  - Follow the instruction on the mini game they are displayed at the beginning of each game and how to restart

---

## Each Game's Functionality

- **Reaction Time** 
  - On start Click anywhere to start, wait for the image to turn green
  - Note: Click to early and you will have to restart
  - You will see a final reaction time in Milliseconds if successful on green click

- **Aim Trainer**
  - On start click the target to start
  - There will be a total of 30 Targets to click
  - Click the targets as fast as possible
  - The end result is a final reactions time average of clicking each target
  - Click target again to restart

- **Chimp Test**
  - On start click "Start Chimp Test"
  - Click the numbers in order 1-n
  - Notice after round 1 you need to memorize where the numbers are because they will be covered by a white block

- **Visual Memory**
  - On start click the start button to begin
  - A grid will show up only click the white boxes to succeed.
  - Note the Boxes to click will only be known for a few seconds
  - Note you will only have three lives to mess up with select three incorrect boxes during round you lose a life

- **Hearing**
  - On start click the start button
  - As soon as you hear the sound click the stop button to get your hertz

- **Typing**
  - On start, start typing and the words per minute will start calculating
  - Type as quickly as you can to get the best time
  - Green means the letter is correct, Orange means your letter was wrong
  - At the end hit spacebar in order to see your score
  - Click restart to start the game again

- **Number Memory**
  - On start a number will show up on the screen
  - The user needs to memorize this number
  - Then ender the number into the text box and click submit
  - Click next to see the next number
  - The next number will show up, memorize this and so on
  - Fail once you will need to restart the whole game
  - Click Try again to get back to start

- **Verbal Memory**
  - On start the user will see the instructions
  - On start click lives, score, a word, a new button, and a seen button
  - If the word has been seen already click seen
  - If the word has not been seen click the new button

---

## Understanding of Code structure

- The Code is running on JavaFX
- It is a mix of FXML and Java files
- **Note** running the JavaFX requires editing the Edit configurations
  - **Steps** 
    - In Intellij 
    - Edit Configurations...
    - Main
    - VM: options: `--module-path
                   C:\Users\smbra\Desktop\CS351\JavaFx\openjfx-14.0.2.1_windows-x64_bin-sdk\javafx-sdk-14.0.2.1\lib
                   --add-modules
                   javafx.controls,javafx.fxml,javafx.media`

## Reference

Resource link to the Human Benchmark website: https://humanbenchmark.com/

---
