class NumArray {

    int[] tree;
    int[] nums;
    int n;

    public NumArray(int[] nums) {
        this.nums=nums;
        n=nums.length;
        tree=new int[4*n];
        build(0,0,n-1);
    }

    private void build(int node,int start,int end) {
        if(start==end) {
            tree[node]=nums[start];
            return;
        }

        int mid=(start+end)/2;

        build(2*node+1,start,mid);
        build(2*node+2,mid+1,end);

        tree[node]=tree[2*node+1]+tree[2*node+2];
    }

    public void update(int index,int val) {
        updateTree(0,0,n-1,index,val);
    }

    private void updateTree(int node,int start,int end,int index,int val) {
        if(start==end) {
            nums[index]=val;
            tree[node]=val;
            return;
        }

        int mid=(start+end)/2;

        if(index<=mid) {
            updateTree(2*node+1,start,mid,index,val);
        } else {
            updateTree(2*node+2,mid+1,end,index,val);
        }

        tree[node]=tree[2*node+1]+tree[2*node+2];
    }

    public int sumRange(int left,int right) {
        return query(0,0,n-1,left,right);
    }

    private int query(int node,int start,int end,int left,int right) {
        if(right<start||left>end) {
            return 0;
        }

        if(left<=start&&end<=right) {
            return tree[node];
        }

        int mid=(start+end)/2;

        return query(2*node+1,start,mid,left,right)+
               query(2*node+2,mid+1,end,left,right);
    }
}
