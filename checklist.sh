#!/bin/zsh
javac com/*.java


echo "";
tput setaf 2; echo "Java: 0.5*X^0 + 3 * X^1 + 0.3 * X^2 = 0.1 * X^0 + 0.0 * X^1"; tput setaf 7;
java com/Main 			"0.5*X^0 + 3 * X^1 + 0.3 * X^2 = 0.1 * X^0 + 0.0 * X^1";
echo "";
tput setaf 2; echo "Java: 5 * X^0 = 5 * X^0"; tput setaf 7;
java com/Main 			"5 * X^0 = 5 * X^0";
echo "";
tput setaf 2; echo "Java: 4 * X^0 = 8 * X^0"; tput setaf 7;
java com/Main 			"4 * X^0 = 8 * X^0";
echo "";
tput setaf 2; echo "Java: 5 * X^0 = 4 * X^0 + 7 * X^1"; tput setaf 7;
java com/Main 			"5 * X^0 = 4 * X^0 + 7 * X^1";
echo "";
tput setaf 2; echo "Java: 5 * X^0 + 13 * X^1 + 3 * X^2 = 1 * X^0 + 1 * X^1"; tput setaf 7;
java com/Main 			"5 * X^0 + 13 * X^1 + 3 * X^2 = 1 * X^0 + 1 * X^1";
echo "";
tput setaf 2; echo "Java: 6 * X^0 + 11 * X^1 + 5 * X^2 = 1 * X^0 + 1 * X^1"; tput setaf 7;
java com/Main 			"6 * X^0 + 11 * X^1 + 5 * X^2 = 1 * X^0 + 1 * X^1";
echo "";
tput setaf 2; echo "Java: 5*X^0 + 3 * X^1 + 3 * X^2 = 1 * X^0 + 0 * X^1"; tput setaf 7;
java com/Main 			"5*X^0 + 3 * X^1 + 3 * X^2 = 1 * X^0 + 0 * X^1";
echo "";
tput setaf 2; echo "(X - 1) * (X - 1) * (X - 1) + 5 = 10"; tput setaf 7;
java com/Main 		"(X - 1) * (X - 1) * (X - 1) + 5 = 10";
echo "";
tput setaf 2; echo "(X - 1) * (X - 1) * (X - 1) + 5 = 10 + X^3"; tput setaf 7;
java com/Main 		"(X - 1) * (X - 1) * (X - 1) + 5 = 10 + X^3";
echo "";


