from typing import Optional, List
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:

    # Problem 1 
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        if not list1:
            return list2
        elif not list2:
            return list1
        elif list1.val < list2.val:
            list1.next = self.mergeTwoLists(list1.next, list2)
            return list1
        else:
            list2.next = self.mergeTwoLists(list1, list2.next)
            return list2

    # Problem 2
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        nodes = []
        for node in lists:
            while node:
                nodes.append(node)
                node = node.next
        nodes.sort(key = lambda x: x.val)
        ans = ListNode(0)
        curr = ans
        for node in nodes:
            curr.next = node
            curr = curr.next
        curr.next = None
        return ans.next

