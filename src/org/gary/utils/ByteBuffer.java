package org.gary.utils;
/**
 * @author gary
 *
 */
public class ByteBuffer {
    private byte[] value;
    private int count;

    public ByteBuffer() {
        this(16);
    }

    public ByteBuffer(int length) {
        value = new byte[length];
    }

    public ByteBuffer(byte[] bytes) {
        this(bytes.length + 16);
        append(bytes);
    }

    public int length() {
        return count;
    }
    
    public void reset(){
//    	value = new byte[16];
    	count = 0;
    }

    public ByteBuffer append(byte str[]) {
        int len = str.length;
        int newcount = count + len;
        if (newcount > value.length)
            expandCapacity(newcount);
        System.arraycopy(str, 0, value, count, len);
        count = newcount;
        return this;
    }

	public ByteBuffer append(byte[] str, int offset, int len) {
        int newcount = count + len;
        if (newcount > value.length)
            expandCapacity(newcount);
        System.arraycopy(str, offset, value, count, len);
        count = newcount;
        return this;
	}
    private void expandCapacity(int minimumCapacity) {
        int newCapacity = (value.length + 1) * 2;
        if (minimumCapacity > newCapacity)
            newCapacity = minimumCapacity;
        byte newValue[] = new byte[newCapacity];
        System.arraycopy(value, 0, newValue, 0, count);
        value = newValue;
    }
    public ByteBuffer tailTrim(int len){
    	if(len<=count)count-=len;
    	return this;
    }

    public ByteBuffer headTrim(int len){
    	if(len>count)return this;
    	System.arraycopy(value, len, value, 0, count-len);
    	count-=len;
    	return this;
    }

    public ByteBuffer append(byte c) {
        int newcount = count + 1;
        if (newcount > value.length)
            expandCapacity(newcount);
        value[count++] = c;
        return this;
    }

    public byte[] getValue() {
        byte[] val = new byte[count];
        System.arraycopy(value, 0, val, 0, count);
        return val;
    }

    public byte[] getValueN(int start, int length) {
        if ((start + length) > count)return null;
        if (length == 0)return new byte[]{};
        byte[] val = new byte[length];
        System.arraycopy(value, start, val, 0, length);
        return val;
    }

    public byte[] getValueN(int start) {
        if (start > (count - 1))
            return null;
        int lentmp = count - start;
        byte[] val = new byte[lentmp];
        System.arraycopy(value, start, val, 0, lentmp);
        return val;
    }

    public int resetValue(byte val) {
        for (int i = 0; i < count; i++)
            value[i] = val;
        return count;
    }

    public int resetValue(int start, int size, byte val) {
        if (start > (count - 1))
            return -1;
        if ((start + size) > count)
            return -1;
        for (int i = 0; i < size; i++)
            value[i + start] = val;
        return size;
    }

    public int replace(int loc, byte val) {
        if (loc > (count - 1))
            return -1;
        value[loc] = val;
        return 1;
    }

    public int replace(int loc, byte[] val, int len) {

        if (loc > (count - 1))
            return -1;
        if ((loc + len) > count)
            return -1;
        int lentemp = len > val.length ? len : val.length;
        byte[] valtemp = new byte[lentemp];

        for (int i = 0; i < val.length; i++)
            valtemp[i] = val[i];
        for (int i = val.length; i < len; i++)
            valtemp[i] = 0x00;
        for (int i = 0; i < len; i++)
            value[loc + i] = valtemp[i];
        return len;
    }

    public ByteBuffer insert(int loc, byte[] val) {
        if (val == null || val.length == 0)
            return this;
        loc = loc > count ? count : loc < 0 ? 0 : loc;
        int count1 = count;
        count += val.length;
        byte[] vala = new byte[count];
        for (int i = 0; i < loc; i++)
            System.arraycopy(value, 0, vala, 0, loc);
        for (int i = 0; i < val.length; i++)
            System.arraycopy(val, 0, vala, loc, val.length);
        for (int i = loc; i < count; i++)
            System.arraycopy(value, loc, vala, loc + val.length, count1 - loc);
        value = vala;
        return this;
    }
    
    public byte getCheckSum(){
    	byte v = 0x00;
    	for(int i=0;i<count;i++){
    		v^=value[i];
    	}
    	return v;
    }
    public byte getByteAt(int loc){
    	 return value[loc];    	
    }
 }
