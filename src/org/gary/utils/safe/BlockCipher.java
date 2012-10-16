package org.gary.utils.safe;

/// A block encryption template.
// <P>
// <A HREF="/resources/classes/Acme/Crypt/BlockCipher.java">Fetch the software.</A><BR>
// <A HREF="/resources/classes/Acme.tar.Z">Fetch the entire Acme package.</A>
// <P>
// @see Cipher
// @see StreamCipher
// @see EncryptedOutputStream
// @see EncryptedInputStream
// @see DesCipher
// @see CbcBlockCipher

public abstract class BlockCipher extends Cipher    {

    /// Constructor.
    public BlockCipher( int keySize, int blockSize )	{
	    super( keySize );
	    this.blockSize = blockSize;
	}

    /// How big a block is.
    public int blockSize;

    /// Return how big a block is.
    public int blockSize()	{
	    return blockSize;
	}

    /// Encrypt a block of bytes.
    public abstract void encrypt( byte[] clearText, int clearOff,
				    byte[] cipherText, int cipherOff );

    /// Decrypt a block of bytes.
    public abstract void decrypt( byte[] cipherText, int cipherOff,
				    byte[] clearText, int clearOff );

    /// Encrypt a block of bytes.
    public void encrypt( byte[] clearText, byte[] cipherText )	{
	    encrypt( clearText, 0, cipherText, 0 );
	}

    /// Decrypt a block of bytes.
    public void decrypt( byte[] cipherText, byte[] clearText )	{
	    decrypt( cipherText, 0, clearText, 0 );
	}

}
