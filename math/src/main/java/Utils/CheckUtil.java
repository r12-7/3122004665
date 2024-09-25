package Utils;

import Bean.BinaryTree;
import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Stack;

public class CheckUtil {
    /**
     * Description:  根据后缀表达式将其转换为二叉树的形式，方便后面判断是否重复并且防止出现负数
     * date: 2024/9/24 20:17
     * @param expression
     * @author: wzr
     * @return :
     */
    public static BinaryTree OptiExpression(String expression) {

        Stack<String> stack = new Stack<String>();
        Stack<BinaryTree> tstack = new Stack<BinaryTree>();
        BinaryTree bt = new BinaryTree();
        String[] express = expression.split("");
        System.out.println(express);
        String s = "";//暂存入栈信息
        for (int i = 0; i < express.length; i++) {
            if (!express[i].equals("+") && !express[i] .equals("-") && !express[i].equals("*")&& !express[i] .equals("÷") && !express[i].equals(",")) {
                s += express[i];
            } else if (express[i] .equals(",")) {
                if (!s.equals("")) {
                    BinaryTree t = new BinaryTree();
                    t.setRoot_data(s);
                    t.setLeft_tree(null);
                    t.setRight_tree(null);
                    tstack.push(t);
                    bt = t;
                }
                s = "";
            } else if (express[i] .equals("+")  || express[i] .equals("-")  || express[i] .equals("*")  || express[i] .equals("÷") ) {
                if (!s.equals("")) {
                    BinaryTree t = new BinaryTree();
                    t.setRoot_data(s);
                    t.setLeft_tree(null);
                    t.setRight_tree(null);
                    tstack.push(t);
                    bt = t;
                }
                    s = "" + express[i];
                    System.out.println("zan" + tstack.size());
                    BinaryTree t1 = new BinaryTree();
                    t1.setRoot_data(s);
                    t1.setRight_tree(tstack.pop());
                    t1.setLeft_tree(tstack.pop());
                    tstack.push(t1);
                    s = "";
                    bt = t1;
                }
            }
            return bt;
        }
    /**
     * Description: 按规则优化二叉树，使其计算过程中不出现负数，并且中序遍历，生成查重使用的表达式，进行查重处理
     * date: 2024/9/24 20:19
     * @param t
     * @author: wzr
     * @return :
     */
    public static BinaryTree AdjustmentTree(BinaryTree t) {
        if(t!=null) {
            BinaryTree left=t.getLeft_tree();
            BinaryTree right=t.getRight_tree();
            //若右子树的值大于左子树，则交换子树
            if(left==null||right==null) {
                return t;
            }
            else if(right.getRoot_data().compareTo(left.getRoot_data())>0) {
                  SwapChildTree(t);
            }
            t.setLeft_tree(AdjustmentTree(t.getLeft_tree()));
            t.setRight_tree(AdjustmentTree(t.getRight_tree()));
        }

        return t;
    }
    /**
     * Description:  将二叉树重新转换为中缀表达式
     * date: 2024/9/24 20:20
     * @param t
     * @author: wzr
     * @return :
     */
    public static String TreeToExpression(BinaryTree t) {
        String left_s="";
        String right_s="";
        String expression="";
        if(t!=null) {
            if(t.getLeft_tree()!=null&&t.getRight_tree()!=null) {
                String left_root=t.getLeft_tree().getRoot_data();
                String right_root=t.getRight_tree().getRoot_data();
                if(left_root.equals("+")||left_root.equals("-")||left_root.equals("*")||left_root.equals("÷")) {
                    left_s="("+TreeToExpression(t.getLeft_tree())+")";
                }
                else {
                    left_s=TreeToExpression(t.getLeft_tree());
                }
                if(right_root.equals("+")||right_root.equals("-")||right_root.equals("*")||right_root.equals("÷")) {
                    right_s="("+TreeToExpression(t.getRight_tree())+")";
                }
                else {
                    right_s=TreeToExpression(t.getRight_tree());
                }

            }

            expression=left_s + t.getRoot_data() + right_s;
        }
        return expression;

    }
    /**
     * Description:  交换二叉树的左右孩子
     * date: 2024/9/25 19:26
     * @param t
     * @author: wzr
     * @return :
     */
   public static void SwapChildTree(BinaryTree t){
        if(t.getLeft_tree()!=null&&t.getRight_tree()!=null){
            BinaryTree left=t.getLeft_tree();
            BinaryTree right=t.getRight_tree();
            BinaryTree temp=new BinaryTree();
            temp=left;
            left=right;
            right=temp;

        }
   }
    }



