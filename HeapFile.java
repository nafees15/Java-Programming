package simpledb;  

import java.io.*;
import java.util.*;

/**
 * HeapFile is an implementation of a DbFile that stores a collection
 * of tuples in no particular order.  Tuples are stored on pages, each of
 * which is a fixed size, and the file is simply a collection of those
 * pages. HeapFile works closely with HeapPage.  The format of HeapPages
 * is described in the HeapPage constructor.
 *
 * @see simpledb.HeapPage#HeapPage
 */
public class HeapFile implements DbFile {

	/**
	 * The File associated with this HeapFile.
	 */
    protected File file;
    
    /**
     * The TupleDesc associated with this HeapFile.
     */
    protected TupleDesc td;

    /**
     * Constructs a heap file backed by the specified file.
     *
     * @param f the file that stores the on-disk backing store for this heap file.
     */
    public HeapFile(File f, TupleDesc td) {
        this.file=f;
        this.td = td;
    }

    /**
     * Returns the File backing this HeapFile on disk.
     *
     * @return the File backing this HeapFile on disk.
     */
    public File getFile() {
        return file;
    }

    /**
    * Returns an ID uniquely identifying this HeapFile. Implementation note:
    * you will need to generate this tableid somewhere ensure that each
    * HeapFile has a "unique id," and that you always return the same value
    * for a particular HeapFile. We suggest hashing the absolute file name of
    * the file underlying the heapfile, i.e. f.getAbsoluteFile().hashCode().
    *
    * @return an ID uniquely identifying this HeapFile.
    */
    public int getId() {
        return file.getAbsoluteFile().hashCode();
    }
    
    /**
     * Returns the TupleDesc of the table stored in this DbFile.
     * @return TupleDesc of this DbFile.
     */
    public TupleDesc getTupleDesc() {
    	return td;
    }

    /*
    // see DbFile.java for javadocs
    public Page readPage(PageId pid) {
        // some code goes here
        throw new UnsupportedOperationException("Implement this");
    }
    */
    
    public Page readPage(PageId pid) {
        
        // table id of required page does not match the table id of this file
        if(pid.getTableId() != getId()) {
                throw new IllegalArgumentException("Page does not exist in this file");
        }
        try {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                       
                        // array to store page data
                        byte[] pageData = new byte[BufferPool.PAGE_SIZE];
                       
                        // get offset into the random access file
                        int offset = pid.pageno() * BufferPool.PAGE_SIZE;
                        randomAccessFile.seek(offset);
                       
                        // read the page data
                        randomAccessFile.read(pageData, 0, BufferPool.PAGE_SIZE);
                        randomAccessFile.close();
                        return new HeapPage((HeapPageId)pid, pageData);
                } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
               
                throw new IllegalArgumentException();
    }



    // see DbFile.java for javadocs
    public void writePage(Page page) throws IOException {
        // some code goes here
        // not necessary for assignment1
    }

    /**
     * Returns the number of pages in this HeapFile.
     */
    public int numPages() {
        return (int) (file.length() / BufferPool.PAGE_SIZE);
    }

    // see DbFile.java for javadocs
    public ArrayList<Page> addTuple(TransactionId tid, Tuple t)
        throws DbException, IOException, TransactionAbortedException {
        // some code goes here
        return null;
        // not necessary for assignment1
    }

    // see DbFile.java for javadocs
    public Page deleteTuple(TransactionId tid, Tuple t)
        throws DbException, TransactionAbortedException {
        // some code goes here
        return null;
        // not necessary for assignment1
    }

    // see DbFile.java for javadocs
    public DbFileIterator iterator(TransactionId tid) {
        // some code goes here
    	
    	PageId pid = null;
    	HeapPageId pageId = new HeapPageId(getId(), pid.pageno() );
        Page page = null;
		try {
			page = Database.getBufferPool().getPage(tid, pageId, Permissions.READ_ONLY);
		} catch (TransactionAbortedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbException e) {
			
			e.printStackTrace();
		}
                                               
                List<Tuple> tupleList = new ArrayList<Tuple>();
               
                // get all tuples from the first page in the file
                // using the HeapPage iterator
                HeapPage hp = (HeapPage)page;
                Iterator<Tuple> itr = hp.iterator();
        while(itr.hasNext()){
                tupleList.add(itr.next());
        }
        return  (DbFileIterator) tupleList;

       // throw new UnsupportedOperationException("Implement this");
    }
        
}

