## reverse-string
### ans
```
class Solution(object):
    def reverseString(self, s):
        l = len(s)
        if l < 2:
            return s
        return self.reverseString(s[l/2:]) + self.reverseString(s[:l/2])
```

## roman-to-integer
```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
```
### ans
https://leetcode.com/problems/roman-to-integer/discuss/6537/My-Straightforward-Python-Solution
```
class Solution:
    def romanToInt(self, s: str) -> int:
        roman = {'M': 1000,'D': 500 ,'C': 100,'L': 50,'X': 10,'V': 5,'I': 1}
        z = 0
        for i in range(0, len(s) - 1):       
            if roman[s[i]] < roman[s[i+1]]:
                z -= roman[s[i]]
            else:
                z += roman[s[i]]
        return z + roman[s[-1]]
```

## first-unique-character-in-a-string
### ans
```
def firstUniqChar(self, s):
    """
    :type s: str
    :rtype: int
    """

    letters='abcdefghijklmnopqrstuvwxyz'
    index=[s.index(l) for l in letters if s.count(l) == 1]
    return min(index) if len(index) > 0 else -1
```
        
## valid-anagram        
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

Example 1:
```
Input: s = "anagram", t = "nagaram"
Output: true        
```

### ans
```
def isAnagram1(self, s, t):
    dic1, dic2 = {}, {}
    for item in s:
        dic1[item] = dic1.get(item, 0) + 1
    for item in t:
        dic2[item] = dic2.get(item, 0) + 1
    return dic1 == dic2
```
```
def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        dic = {}
        for i in s:
            if i not in dic:
                dic[i] = 1
            else:
                dic[i] += 1
        
        for j in t:
            if j not in dic:
                return False
            else:
                dic[j] -= 1
        
        for val in dic.values():
            if val != 0:
                return False
        
        return True
```

## implement-strstr
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
```
Input: haystack = "hello", needle = "ll"
Output: 2
```

### ans
```
public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}
```

## count-and-say
```
Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
```

### my ans
```
class Solution:
    def countAndSay(self, n: int) -> str:
        if (n==1) :
            return "1"
        
        previous = self.countAndSay(n-1)
        
        result = ""
        counter = 0
        previousWord = previous[0]
        for word in previous:
            if previousWord != word:
                result += str(counter) + previousWord
                counter = 1
                previousWord = word
            else:
                counter += 1
        result += str(counter) + previousWord        
        
        return result
```
### ans
```
def countAndSay(n):
    result = "1"
    for _ in range(n - 1):
        # original
        # s = ''.join(str(len(list(group))) + digit for digit, group in itertools.groupby(s))
        
        # decomposed
        v = '' # accumulator string
        # iterate the characters (digits) grouped by digit
        for digit, group in itertools.groupby(result):
            count = len(list(group)) # eg. the 2 in two 1s 
            v += "%i%s" % (count, digit) # create the 21 string and accumulate it
        result = v # save to result for the next for loop pass

    # return the accumulated string
    return result
```

## longest-common-prefix
```
Input: strs = ["flower","flow","flight"]
Output: "fl"
```
### my ans
```
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        commonPrefix = strs[0]
        
        for i in range(1,len(strs)):
            for j in range(len(commonPrefix)):
                if j > len(strs[i])-1:
                    commonPrefix = commonPrefix[:j]   
                    break
                if commonPrefix[j] != strs[i][j]:
                    if  i==0 :
                        return ""
                    else:
                        commonPrefix = commonPrefix[:j]                        
                        break
        
        return commonPrefix
```        
        
### ans
```
 def longestCommonPrefix(self, strs):
        if not strs:
            return ""
        shortest = min(strs,key=len)
        for i, ch in enumerate(shortest):
            for other in strs:
                if other[i] != ch:
                    return shortest[:i]
        return shortest 
```        
