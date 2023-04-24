import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Program {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String lines = br.readLine();

    String[] strs = lines.trim().split("\\s+");

    int n = Integer.parseInt(strs[0]);
    int m = Integer.parseInt(strs[1]);
    int q = Integer.parseInt(strs[2]);
    DataCenter[] centers = new DataCenter[n];

    for (int i = 0; i < n; i++) {
      centers[i] = new DataCenter(m);
    }

    for (int i = 0; i < q; i++) {
      String command = br.readLine();
      String[] splited = command.split(" ");
      if (splited.length == 1) {
        // GETMAX
        int max = 0;
        int min = m * n;
        int minCenter = 1;
        int maxCenter = 1;
        for (int j = 0; j < n; j++) {
          int currentMax = centers[j].events * centers[j].active;
          if (currentMax > max) {
            max = currentMax;
            maxCenter = j + 1;
          }
          if (currentMax < min) {
            min = currentMax;
            minCenter = j + 1;
          }
        }

        if ("GETMAX".equals(splited[0])) {
          System.out.println(maxCenter);
        }
        if ("GETMIN".equals(splited[0])) {
          System.out.println(minCenter);
        }

      } else if (splited.length == 2) {

        // RESET
        int datacIndex = Integer.parseInt(splited[1]) - 1;
        centers[datacIndex].ResetServers(m);
        centers[datacIndex].events += 1;

      } else {

        // DISABLE
        int datacIndex = Integer.parseInt(splited[1]) - 1;
        int serverIndex = Integer.parseInt(splited[2]) - 1;

        centers[datacIndex].servers[serverIndex] = 0;
        centers[datacIndex].active -= 1;

      }
    }
  }
}

class DataCenter {
  int events;
  int[] servers;
  int active;

  public DataCenter(int size) {
    servers = new int[size];
    ResetServers(size);
  }

  void ResetServers(int size) {
    for (int i = 0; i < size; i++) {
      servers[i] = 1;
    }
    active = size;
  }
}
