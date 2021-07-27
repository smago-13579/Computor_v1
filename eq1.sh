#!/bin/zsh
javac com/*.java


echo "";
tput setaf 2; echo "Java: 10 * X - 10 + 5 * X ^ 2 = X ^ 2 "; tput setaf 7;
java com/Main "10 * X - 10 + 5 * X ^ 2 = X ^ 2 ";
echo "";
tput setaf 2; echo "Java: 10 * X - 10 + 5 * X ^ 2 / 5 = X ^ 2 / X ^ 2 "; tput setaf 7;
java com/Main "10 * X - 10 + 5 * X ^ 2 / 5 = X ^ 2 / X ^ 2 ";
echo "";
tput setaf 2; echo "Java: 10 * X / 12 - 10 * X^0 + 0 * 51 * X ^ 2 / 5 = 0 / X"; tput setaf 7;
java com/Main "10 * X / 12 - 10 * X^0 + 0 * 51 * X ^ 2 / 5 = 0 / X";
echo "";
tput setaf 2; echo "Java: 10 * X / 12 - 10 * X^0 + 0 * 51 * X ^ 2 / 5 = 0 / 10"; tput setaf 7;
java com/Main "10 * X / 12 - 10 * X^0 + 0 * 51 * X ^ 2 / 5 = 0 / 10";
echo "";
tput setaf 2; echo "Java: 10 * X * X / X^2 + 0 * 51 * X ^ 2 / 5 = 0 / 10"; tput setaf 7;
java com/Main "10 * X * X / X^2 + 0 * 51 * X ^ 2 / 5 = 0 / 10";
echo "";
tput setaf 2; echo "Java: X^2 + 10 * X - 25 + 3 * X ^ 2 = 10"; tput setaf 7;
java com/Main "X^2 + 10 * X - 25 + 3 * X ^ 2 = 10";
echo "";
tput setaf 2; echo "Java: (X^2 + 5*X^2) - 10 * X - (25 + 3) * X ^ 2 = 10"; tput setaf 7;
java com/Main "(X^2 + 5*X^2) - 10 * X - (25 + 3) * X ^ 2 = 10";
echo "";
tput setaf 2; echo "Java: (X^2 - 5*X^2) - 1 * X - (25 + 3) * X ^ 2 = 10"; tput setaf 7;
java com/Main "(X^2 - 5*X^2) - 1 * X - (25 + 3) * X ^ 2 = 10";
echo "";
tput setaf 2; echo "Java: (X^2 -10*X + 5*X^2 + 5*X) - X - (25 + 3) * X ^ 2 = (10 + 10 + X)"; tput setaf 7;
java com/Main "(X^2 -10*X + 5*X^2 + 5*X) - X - (25 + 3) * X ^ 2 = (10 + 10 + X)";
echo "";
tput setaf 2; echo "Java: (X^2 -10*X + 5*X^2 + 5*X - 6*X^2 + 5*X) - X - (25 + 3) * X ^ 2 = (10 + 10 + X)"; tput setaf 7;
java com/Main "(X^2 -10*X + 5*X^2 + 5*X - 6*X^2 + 5*X) - X - (25 + 3) * X ^ 2 = (10 + 10 + X)";
echo "";
tput setaf 2; echo "5 * (X * 6 - 15 * 3) + 5 = 10"; tput setaf 7;
java com/Main "5 * (X * 6 - 15 * 3) + 5 = 10";
echo "";
tput setaf 2; echo "(X - 1) * (X * 6 - 15 * 3) + 5 = 10"; tput setaf 7;
java com/Main "(X - 1) * (X * 6 - 15 * 3) + 5 = 10";
echo "";
tput setaf 2; echo "1 - (X * 6 - 15 * 3) + 5 = 10"; tput setaf 7;
java com/Main "1 - (X * 6 - 15 * 3) + 5 = 10";
echo "";
tput setaf 2; echo "(X - 1) * (X - 1) * (X - 1) + 5 = 10"; tput setaf 7;
java com/Main "(X - 1) * (X - 1) * (X - 1) + 5 = 10";
echo "";
tput setaf 2; echo "6 * X^0 + 11 * X^1+5* X^2 = 1*X^0 + 1 * X^1"; tput setaf 7;
java com/Main "6 * X^0 + 11 * X^1+5* X^2 = 1*X^0 + 1 * X^1";
echo "";
tput setaf 2; echo "1*A^0 + 2 * A^1 + A^2 = 0"; tput setaf 7;
java com/Main "1*A^0 + 2 * A^1 + A^2 = 0";
echo "";













