#!/bin/zsh
javac com/*.java


echo "";
tput setaf 2; echo "Java: 10 * X - 10 + 5 * X ^ 2 = X ^ 2 "; tput setaf 7;
java com/Main "10 * X - 10 + 5 * X ^ 2 = X ^ 2 ";
echo "";
tput setaf 2; echo "Java: 10 * X - 10 + 5 * X ^ 2 / 5 = X ^ 2 / X ^ 2 "; tput setaf 7;
java com/Main "10 * X - 10 + 5 * X ^ 2 / 5 = X ^ 2 / X ^ 2 ";
echo "";
tput setaf 2; echo "Java: 10 * X * X - 10 + 5 * X ^ 2 / 5 = X ^ 2 / X ^ 2 * 10 / X"; tput setaf 7;
java com/Main "10 * X * X - 10 + 5 * X ^ 2 / 5 = X ^ 2 / X ^ 2 * 10 / X";
tput setaf 2; echo "Invalid operation: 10.0 / X^1"; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 10 * X * X - (10 + 5) * X ^ 2 / 5 = X ^ 2 / X ^ 2 * 10 / X"; tput setaf 7;
java com/Main "10 * X * X - (10 + 5) * X ^ 2 / 5 = X ^ 2 / X ^ 2 * 10 / X";
tput setaf 2; echo "Invalid operation: 10.0 / X^1"; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 10 * X * X / 0 - (10 + 5) * X ^ 2 / 5 = X ^ 2 / X ^ 2 * 10 / X"; tput setaf 7;
java com/Main "10 * X * X / 0 - (10 + 5) * X ^ 2 / 5 = X ^ 2 / X ^ 2 * 10 / X";
tput setaf 2; echo "Can't divide by zero: 10.0 * X^2 / 0.0"; tput setaf 7;
echo "";
