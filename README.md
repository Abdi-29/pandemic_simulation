# pandemic_simulation

The current pandemic is of course all over the news these days. Trying to get it
under control requires the right information and one of the key pieces of
information is how the virus spreads. In order to investigate this, simulations of the
spread of the virus are used.
Your mission, should you choose to accept it, is to build a simulation (using JAVA)
that models this spread on a grid of `n*n` persons. The simulation, running over the
course of a number of iterations, or `rounds`, will be based on the simple
assumption that the virus spreads through contact between adjacent people. If
the number of contacts reaches a certain threshold, a person will become infected.
When an infected person is surrounded by more than a certain threshold of
infected people, the virus will die out and that person will recover and become
uninfected again.
For example, a simulation on an 8x8 grid, with 7 rounds, an infection threshold of 3,
a recovery threshold of 6 and 3 infected persons to start with can be run with a
command similar to the following:<br>
`java pandemic 8 7 3 6 [<4,7>,<4,8>,<5,8>,<6,8>]`<br>
After a number of rounds the output should be displayed.

### The rules
The spread of the pandemic is covered by a limited set of rules:
- Any person with a number of infected contacts larger than the infection
  threshold will become infected
- Any infected person with more than the recovery threshold infected
  contacts, will recover and become uninfected.
  - All others don’t change
 
A “contact” is defined as a person that’s directly adjacent to the infected,
horizontally, vertically or diagonally (not wrapping around the edges of the grid)

### In order to run this code:

./run.sh
