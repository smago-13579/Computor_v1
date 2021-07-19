#!/bin/zsh
javac com/*.java

echo "";
tput setaf 2; echo "Java: 123 + )(X ^ 2 - 15) = 12"; tput setaf 7;
java com/Main "123 + )(X ^ 2 - 15) = 12";
tput setaf 2; echo "The open parenthesis is missing - \"(\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 123 + ((X ^ 2 - 15) = 12"; tput setaf 7;
java com/Main "123 + ((X ^ 2 - 15) = 12";
tput setaf 2; echo "The closed parenthesis is missing - \")\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 123 + (X ^ 2 - 15 = 12"; tput setaf 7;
java com/Main "123 + (X ^ 2 - 15 = 12";
tput setaf 2; echo "The closed parenthesis is missing - \")\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 123 + X^0 = (X ^ 2 - 15) = 12"; tput setaf 7;
java com/Main "123 + X^0 = (X ^ 2 - 15) = 12";
tput setaf 2; echo "Incorrect equality - \"=\" "; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 123 + X^0 "; tput setaf 7;
java com/Main "123 + X^0 ";
tput setaf 2; echo "Incorrect equality - \"=\" "; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 123 + (X^0 = (X ^ 2 - 15)) + 12"; tput setaf 7;
java com/Main "123 + (X^0 = (X ^ 2 - 15)) + 12";
tput setaf 2; echo "The closed parenthesis is missing - \")\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: +123 + X - X ^ 2 - 15 = 12"; tput setaf 7;
java com/Main "+123 + X - X ^ 2 - 15 = 12";
tput setaf 2; echo "Invalid operator - \"+\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: +123 + X - X ^ 2 - 15 = 12"; tput setaf 7;
java com/Main "+123 + X - X ^ 2 - 15 = 12";
tput setaf 2; echo "Invalid operator - \"+\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 11 + X - + X = X ^ 2 "; tput setaf 7;
java com/Main "11 + X - + X = X ^ 2 ";
tput setaf 2; echo "Invalid operators - \"-\" and \"+\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: + X = X ^ 2 "; tput setaf 7;
java com/Main "+ X = X ^ 2 ";
tput setaf 2; echo "Invalid operator - \"+\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: 11 + X -  = X ^ 2 "; tput setaf 7;
java com/Main "11 + X -  = X ^ 2 ";
tput setaf 2; echo "Invalid operators - \"-\" and \"=\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - ) = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - ) = X ^ 2 ";
tput setaf 2; echo "Variable is missing after operator - \"-\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10)9 = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10)9 = X ^ 2 ";
tput setaf 2; echo "Operator is missing after - \")\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10)X = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10)X = X ^ 2 ";
tput setaf 2; echo "Operator is missing after - \")\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10)(X + 1) = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10)(X + 1) = X ^ 2 ";
tput setaf 2; echo "Invalid parentheses - )("; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10) * () = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10) * () = X ^ 2 ";
tput setaf 2; echo "Invalid parentheses - ()"; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10) * (-) = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10) * (-) = X ^ 2 ";
tput setaf 2; echo "Variable is missing after operator - \"-\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10) * (/ X^2) = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10) * (/ X^2) = X ^ 2 ";
tput setaf 2; echo "Invalid operator \"/\" after \"(\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10) * X 2 = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10) * X 2 = X ^ 2 ";
tput setaf 2; echo "Invalid Expression: \"X 2.0\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: X (11 + X - 10) * X ^2 = X ^ 2 "; tput setaf 7;
java com/Main "X (11 + X - 10) * X ^2 = X ^ 2 ";
tput setaf 2; echo "Invalid Expression: \"X (\""; tput setaf 7;
echo "";
tput setaf 2; echo "Java: (11 + X - 10) * A^2 = X ^ 2 "; tput setaf 7;
java com/Main "(11 + X - 10) * A^2 = X ^ 2 ";
tput setaf 2; echo "Different Variables: \"X and A\""; tput setaf 7;
echo "";


java com/Main "10 * X ^ 2 = 0 + ."
echo "";
java com/Main "10 * X ^ 1 = 50"
echo "";














