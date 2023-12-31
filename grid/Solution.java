import java.util.*;

class Solution {

  // Problem 1
  public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    boolean[][] vis = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (dfs(board, i, j, 0, vis, word)) return true;
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, int i, int j, int k, boolean[][] vis, String word) {
    boolean ans = false;
    if (word.charAt(k) != board[i][j]) return ans;
    if (k == word.length() - 1) return true;
    vis[i][j] = true;
    int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    for (int[] di: directions) {
      int x = i + di[0];
      int y = j + di[1];
      if (!(0 <= x && x < board.length && 0 <= y && y < board[0].length)) continue;
      if (vis[x][y]) continue;
      ans = ans || dfs(board, x, y, k+1, vis, word);
    }
    vis[i][j] = false;
    return ans;
  }

  // Problem 2
  public List<String> letterCombinations(String digits) {
    char[][] letters = {
    {},
    {},
    {'a', 'b', 'c'},
    {'d', 'e', 'f'},
    {'g', 'h', 'i'},
    {'j', 'k', 'l'},
    {'m', 'n', 'o'},
    {'p', 'q', 'r', 's'},
    {'t', 'u', 'v'},
    {'w', 'x', 'y', 'z'}
    };
    String acc = "";
    List<String> ans = new LinkedList<>();
    if (digits.isEmpty()) return ans;
    f(digits, 0, letters, acc, ans);
    return ans;
  }
  private void f(String digits, int i, char[][] letters, String acc, List<String> ans) {
    if (i == digits.length()) {
      ans.add(acc);
      return;
    }
    int idx = digits.charAt(i) - '0';
    for (char c : letters[idx]) {
      f(digits, i+1, letters, acc + c, ans);
    }
  }

  // Problem 3
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new LinkedList<>();
    char[][] board = new char[n][n];
    for (char[] c: board) Arrays.fill(c, '.');
    boolean[] col = new boolean[n];
    boolean[] pos = new boolean[2*n -1];
    boolean[] neg = new boolean[2*n -1];
    g(board, 0, col, pos, neg, ans);
    return ans;
  }

  private void g(char[][] board, int i, boolean[] col, boolean[] pos, boolean[] neg, List<List<String>> ans) {
    int n = board.length;
    if (i == n) {
      List<String> acc = new LinkedList<>();
      for (char[] c: board) acc.add(new String(c));
      ans.add(acc);
      return;
    }
    for (int j = 0; j < n; j++) {
      if (col[j]) continue;
      if (pos[i-j+(n-1)]) continue;
      if (neg[i+j]) continue;
      col[j] = true;
      pos[i-j+(n-1)] = true;
      neg[i+j] = true;
      board[i][j] = 'Q';
      g(board, i+1, col, pos, neg, ans);
      col[j] = false;
      pos[i-j+(n-1)] = false;
      neg[i+j] = false;
      board[i][j] = '.';
    }
  }

  // Problem 4
  public int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          count++;
          dfs(i, j, grid);
        }
      }
    }
    return count;
  }
  private void dfs(int i,int j, char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    grid[i][j] = '0';
    int[][] directions = {{1,0}, {0,1}, {-1, 0}, {0, -1}};
    for (int[] di: directions) {
      int x = i + di[0];
      int y = j + di[1];
      if (!(0 <= x && x < m && 0 <= y && y < n)) continue;
      if (grid[x][y] == '0') continue;
      dfs(x, y , grid);
    }
  }

  // Problem 5
  public int maxAreaOfIsland(int[][] grid) {
    int ans = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          ans = Math.max(ans, count(i, j, grid));
        }
      }
    }
    return ans;
  }
  private int count(int i, int j, int[][] grid) {
    grid[i][j] = 0;
    int ans = 1;
    int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    int m = grid.length;
    int n = grid[0].length;
    for (int[] di: directions) {
      int x = i + di[0];
      int y = j + di[1];
      if (!(0 <= x && x < m && 0 <= y && y < n)) continue;
      if (grid[x][y] == 0) continue;
      ans += count(x, y, grid);
    }
    return ans;
  }

  // Problem 6
  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<int[]> pacific = new LinkedList<>();
    List<int[]> atlantic = new LinkedList<>();
    int m = heights.length;
    int n = heights[0].length;
    for (int i = 0; i < m; i++) {
      pacific.add(new int[]{i, 0});
      atlantc.add(new int[]{i, n-1});
    }
    for (int j = 0; j < n; j++) {
      pacific.add(new int[]{0, j});
      atlantc.add(new int[]{m-1, j});
    }
    boolean[][] p = new boolean[m][n];
    boolean[][] a = new boolean[m][n];
    ocean(pacific, p, heights);
    ocean(atlantc, a, heights);
    List<List<Integer>> ans = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (p[i][j] && a[i][j]) {
          ans.add(Arrays.asList(i,j));
        }
      }
    }
    return ans;
  }
  private void ocean(List<int[]> start,boolean[][] vis, int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    Stack<int[]> dfs = new Stack<>();
    dfs.addAll(start);
    for (int[] coord: start) vis[coord[0]][coord[1]] = true;
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    while (!dfs.isEmpty()) {
      int[] coord = dfs.pop();
      int i = coord[0];
      int j = coord[1];
      for (int[] di: directions) {
        int x = i + di[0];
        int y = j + di[1];
        if (!(0 <= x && x < m && 0 <= y && y < n)) continue;
        if (vis[x][y]) continue;
        if (grid[i][j] > grid[x][y]) continue;
        vis[x][y] = true;
        dfs.push(new int[]{x, y});
      }
    }
  }

  // Problem 7
  public void solve(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    boolean[][] vis = new boolean[m][n];
    for (int i = 0;i < m; i++) {
      if (!vis[i][0] && board[i][0] == 'O') {
        fill(i, 0, vis, board);
      }
      if (!vis[i][n-1] && board[i][n-1] == 'O') {
        fill(i, n-1, vis, board);
      }
    }
    for (int j = 0; j < n-1; j++) {
      if (!vis[0][j] && board[0][j] == 'O') {
        fill(0, j, vis, board);
      }
      if (!vis[m-1][j] && board[m-1][j] == 'O') {
        fill(m-1, j, vis, board);
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!vis[i][j]) board[i][j] = 'X';
      }
    }
  }
  private void fill(int i, int j, boolean[][] vis, char[][] board) {
    int[][] directions = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
    int m = board.length;
    int n = board[0].length;
    vis[i][j] = true;
    for (int[] di: directions) {
      int x = i + di[0];
      int y = j + di[1];
      if (!(0 <= x && x < m && 0 <= y && y < n)) continue;
      if (vis[x][y]) continue;
      if (board[x][y] == 'X') continue;
      fill(x,y,vis,board);
    }
  }

  // Problem 8
  public int orangesRotting(int[][] grid) {
    Queue<int[]> level = new ArrayDeque<>();
    int m = grid.length;
    int n = grid[0].length;
    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          level.offer(new int[]{i, j});
        } else if (grid[i][j] == 1) {
          count++;
        }
      }
    }
    int[][] directions = {{1,0}, {0,1}, {-1, 0}, {0,-1}};
    int time = 0;
    while (!level.isEmpty()) {
      int K = level.size();
      for (int k = 0; k < K; k++) {
        int[] arr = level.remove();
        int i = arr[0];
        int j = arr[1];
        for (int[] di: directions) {
          int x = i + di[0];
          int y = j + di[1];
          if (!(0 <= x && x < m && 0 <= y && y < n)) continue;
          if (grid[x][y] == 0) continue;
          if (grid[x][y] == 2) continue;
          level.offer(new int[]{x,y});
          grid[x][y] = 2;
          count--;
        }
      }
      if (level.size() > 0) time++;
    }
    return count == 0 ? time : -1;
  }
}


