#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
using namespace std;

int LCS(string& A, string& B) {
	int A_size = A.length();
	int B_size = B.length();
	vector<vector<int>>dp(A_size + 1, vector<int>(B_size + 1, 0));
	for (int i = 1; i <= A_size; ++i) {
		for (int j = 1; j <= B_size; ++j) {
			if (A[i - 1] == B[j - 1]) {
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			else {
				dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
	}
	return dp[A_size][B_size];
}

int main() {
	string A, B;
	cin >> A >> B;
	cout << LCS(A, B);
}