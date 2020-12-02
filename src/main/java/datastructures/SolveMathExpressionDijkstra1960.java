package datastructures;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Purpose of SolveMathExpressionDijkstra1960 is
 */
public class SolveMathExpressionDijkstra1960 {

    @Test
    public void t1(){
        assertEquals(3, solve("(1+2)"));

        assertEquals(6, solve("((1+2)+3)"));

        assertEquals(12, solve("(((1+2)+3)*2)"));
    }

    public int solve(String exp){
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque();
        for(int i = 0; i < exp.length(); i++){
            char c = exp.charAt(i);
            switch(c){
                case '(':
                    break;
                case ')':{
                    Character op = operators.pop();
                    switch(op){
                        case '+':{
                            operands.push((int)operands.pop() + (int)operands.pop());
                            break;
                        }
                        case '*':{
                            operands.push(operands.pop() * operands.pop());
                            break;
                        }
                    }
                    break;
                }
                case '+':
                case '*':
                    operators.push(c);
                    break;
                default:
                    operands.push(Integer.parseInt(String.valueOf(c)));

            }
        }

        return operands.size() > 0 ? (Integer)operands.pop() : 0;
    }
}
