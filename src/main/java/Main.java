import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SG0224789 on 3/2/2016.
 */
public class Main {

  void sync(Db db,String path) throws IOException, InterruptedException, SQLException, ParseException {

    Path mypaths = Paths.get(path);
    WatchService watchService = mypaths.getFileSystem().newWatchService();
    mypaths.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    ExecutorService es = Executors.newCachedThreadPool();
    for(;;) {
      WatchKey watchKey = watchService.take();
      List<WatchEvent<?>> events = watchKey.pollEvents();

      for (WatchEvent event : events) {
        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
          String file = path+"/"+event.context().toString();
          es.execute(new Worker(file,db));
        }
      }
      watchKey.reset();
    }
  }
  public static void main(String[] args) throws Exception {
    Db db = new Db();
    Main m = new Main();
    if(args.length > 0) {
      m.sync(db, args[0]);
    } else {
      System.out.println("usage: run.cmd <watch-folder>");
    }
  }
}
