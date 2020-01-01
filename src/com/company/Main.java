package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    class UF {
        int[] parents;
        int[] size;
        int group;

        UF(int N) {
            parents = new int[N + 1];
            size = new int[N + 1];
            group = N;
            for (int i = 0; i <= N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }

        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP == rootQ)
                return;
            if (size[p] >= size[q]) {
                parents[rootQ] = rootP;
                size[p] += size[q];
            } else {
                parents[rootP] = rootQ;
                size[q] += size[p];
            }
            group--;
        }
    }

    public int minimumCost(int N, int[][] connections) {
        if (connections.length < N - 1)
            return -1;
        UF graph = new UF(N);
        Arrays.sort(connections, (c1, c2) -> c1[2] - c2[2]);
        int res = 0;
        for (int[] connection : connections) {
            if (graph.find(connection[0]) == graph.find(connection[1]))
                continue;
            graph.union(connection[0], connection[1]);
            res += connection[2];
        }
        return res;
    }

}