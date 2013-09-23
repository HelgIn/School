//package listeners;
//
//import io.Message;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//
//
//public class AddTrain implements ActionListener {
//    private ObjectOutputStream obj;
//    private Message msg = null;
//    public AddTrain(ObjectOutputStream obj) {
//         this.obj = obj;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("click");
//        msg = new Message();
//        try {
//            obj.writeObject(msg);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }  finally {
//            try {
//                obj.flush();
//               // obj.close();
//            } catch (IOException e1) {
//                System.out.println("Data send error");
//            }
//        }
//
//    }
//}