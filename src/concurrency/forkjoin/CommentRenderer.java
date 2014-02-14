package concurrency.forkjoin;

import domain.Comment;
import domain.Task;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  User: nicorama
 * Date: 10/09/13 ; Time: 09:53
 */
public class CommentRenderer {

    

    List<Comment> comments;


    public CommentRenderer(List<Comment> comments){
        this.comments = comments;
    }

    List<Comment> comments(){
        return comments;
    }

    public StringBuilder render() {
    
        return new StringBuilder();
    }

    public static void main(String[] args) throws IOException {

        List<Comment> comments = new ArrayList<Comment>();
        for (int i = 0 ; i < 80 ; i++){
            comments.add(new Comment("comment " +i));
        }
        //System.out.println(result.toString());

    }
}
