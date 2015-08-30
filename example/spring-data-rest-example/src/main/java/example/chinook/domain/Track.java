/**
	* Copyright (c) minuteproject, minuteproject@gmail.com
	* All rights reserved.
	* 
	* Licensed under the Apache License, Version 2.0 (the "License")
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	* http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	* 
	* More information on minuteproject:
	* twitter @minuteproject
	* wiki http://minuteproject.wikispaces.com 
	* blog http://minuteproject.blogspot.net
	* 
*/
/**
	* template reference : 
	* - Minuteproject version : 0.9.5
	* - name      : DomainEntityJPA2Annotation
	* - file name : DomainEntityJPA2Annotation.vm
	* - time      : 2015/08/30 n. Chr. at 09:45:11 MESZ
*/
package example.chinook.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * <p>Title: Track</p>
 *
 * <p>Description: Domain Object describing a Track entity</p>
 *
 */
@Entity (name="Track")
@Table (name="\"track\"")
@NamedQueries ({
	 @NamedQuery(name="Track.findAll", query="SELECT a FROM Track a")
	,@NamedQuery(name="Track.findByName", query="SELECT a FROM Track a WHERE a.name = :name")
	,@NamedQuery(name="Track.findByNameContaining", query="SELECT a FROM Track a WHERE a.name like :name")

	,@NamedQuery(name="Track.findByComposer", query="SELECT a FROM Track a WHERE a.composer = :composer")
	,@NamedQuery(name="Track.findByComposerContaining", query="SELECT a FROM Track a WHERE a.composer like :composer")

	,@NamedQuery(name="Track.findByMilliseconds", query="SELECT a FROM Track a WHERE a.milliseconds = :milliseconds")

	,@NamedQuery(name="Track.findByBytes", query="SELECT a FROM Track a WHERE a.bytes = :bytes")

	,@NamedQuery(name="Track.findByUnitprice", query="SELECT a FROM Track a WHERE a.unitprice = :unitprice")

})

public class Track implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Track.findAll";
    public static final String FIND_BY_NAME = "Track.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Track.findByNameContaining";
    public static final String FIND_BY_COMPOSER = "Track.findByComposer";
    public static final String FIND_BY_COMPOSER_CONTAINING ="Track.findByComposerContaining";
    public static final String FIND_BY_MILLISECONDS = "Track.findByMilliseconds";
    public static final String FIND_BY_BYTES = "Track.findByBytes";
    public static final String FIND_BY_UNITPRICE = "Track.findByUnitprice";
	
    @Id @Column(name="TrackId" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer trackid;

//MP-MANAGED-ADDED-AREA-BEGINNING @Name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @Name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-Name@
    @Column(name="Name"  , length=200 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @Composer-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @Composer-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-Composer@
    @Column(name="Composer"  , length=220 , nullable=true , unique=false)
    private String composer; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @Milliseconds-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @Milliseconds-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-Milliseconds@
    @Column(name="Milliseconds"   , nullable=false , unique=false)
    private Integer milliseconds; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @Bytes-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @Bytes-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-Bytes@
    @Column(name="Bytes"   , nullable=true , unique=false)
    private Integer bytes; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @UnitPrice-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @UnitPrice-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-UnitPrice@
    @Column(name="UnitPrice"   , nullable=false , unique=false)
    private java.math.BigDecimal unitprice; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="AlbumId", referencedColumnName = "AlbumId" , nullable=true , unique=false , insertable=true, updatable=true) 
    private Album albumid;  

    @Column(name="AlbumId"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer albumid_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="GenreId", referencedColumnName = "GenreId" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Genre genreid;  

    @Column(name="GenreId"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer genreid_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="MediaTypeId", referencedColumnName = "MediaTypeId" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Mediatype mediatypeid;  

    @Column(name="MediaTypeId"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer mediatypeid_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @invoicelineTrackViaTrackid-field-track@
    @OneToMany (targetEntity=example.chinook.domain.Invoiceline.class, fetch=FetchType.LAZY, mappedBy="trackid", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Invoiceline> invoicelineTrackViaTrackid = new HashSet<Invoiceline>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @m2m-playlistPlaylisttrackViaPlaylistid-track@
    @ManyToMany
    @JoinTable(name="PLAYLISTTRACK", 
        joinColumns=@JoinColumn(name="TrackId"), 
        inverseJoinColumns=@JoinColumn(name="PlaylistId") 
    )
    private Set <Playlist> playlistPlaylisttrackViaPlaylistid = new HashSet <Playlist> (); 
// playlisttrack.PlaylistId->playlist.PlaylistId -- playlisttrack.PlaylistId->playlist.PlaylistId
// PLAYLIST_PLAYLISTTRACK_VIA_PLAYLISTID

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Track() {
    }

	/**
	* All field constructor 
	*/
    public Track(
       Integer trackid,
       String name,
       Integer albumid,
       Integer mediatypeid,
       Integer genreid,
       String composer,
       Integer milliseconds,
       Integer bytes,
       java.math.BigDecimal unitprice) {
	 this(
       trackid,
       name,
       albumid,
       mediatypeid,
       genreid,
       composer,
       milliseconds,
       bytes,
       unitprice
	 ,true);
	}
    
	public Track(
       Integer trackid,
       String name,
       Integer albumid,
       Integer mediatypeid,
       Integer genreid,
       String composer,
       Integer milliseconds,
       Integer bytes,
       java.math.BigDecimal unitprice	
    , boolean setRelationship) {
       //primary keys
       setTrackid (trackid);
       //attributes
       setName (name);
       setComposer (composer);
       setMilliseconds (milliseconds);
       setBytes (bytes);
       setUnitprice (unitprice);
       //parents
       if (setRelationship) this.albumid = new Album();
       if (setRelationship) this.albumid.setAlbumid(albumid); 
	   setAlbumid_ (albumid);
       if (setRelationship) this.genreid = new Genre();
       if (setRelationship) this.genreid.setGenreid(genreid); 
	   setGenreid_ (genreid);
       if (setRelationship) this.mediatypeid = new Mediatype();
       if (setRelationship) this.mediatypeid.setMediatypeid(mediatypeid); 
	   setMediatypeid_ (mediatypeid);
    }

	public Track flat() {
	   return new Track(
          getTrackid(),
          getName(),
          getAlbumid_(),
          getMediatypeid_(),
          getGenreid_(),
          getComposer(),
          getMilliseconds(),
          getBytes(),
          getUnitprice()
       , false
	   );
	}

    public Integer getTrackid() {
        return trackid;
    }
	
    public void setTrackid (Integer trackid) {
        this.trackid =  trackid;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-Name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-Composer@
    public String getComposer() {
        return composer;
    }
	
    public void setComposer (String composer) {
        this.composer =  composer;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-Milliseconds@
    public Integer getMilliseconds() {
        return milliseconds;
    }
	
    public void setMilliseconds (Integer milliseconds) {
        this.milliseconds =  milliseconds;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-Bytes@
    public Integer getBytes() {
        return bytes;
    }
	
    public void setBytes (Integer bytes) {
        this.bytes =  bytes;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-UnitPrice@
    public java.math.BigDecimal getUnitprice() {
        return unitprice;
    }
	
    public void setUnitprice (java.math.BigDecimal unitprice) {
        this.unitprice =  unitprice;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public Album getAlbumid () {
    	return albumid;
    }
	
    public void setAlbumid (Album albumid) {
    	this.albumid = albumid;
    }

    public Integer getAlbumid_() {
        return albumid_;
    }
	
    public void setAlbumid_ (Integer albumid) {
        this.albumid_ =  albumid;
    }
	
    public Genre getGenreid () {
    	return genreid;
    }
	
    public void setGenreid (Genre genreid) {
    	this.genreid = genreid;
    }

    public Integer getGenreid_() {
        return genreid_;
    }
	
    public void setGenreid_ (Integer genreid) {
        this.genreid_ =  genreid;
    }
	
    public Mediatype getMediatypeid () {
    	return mediatypeid;
    }
	
    public void setMediatypeid (Mediatype mediatypeid) {
    	this.mediatypeid = mediatypeid;
    }

    public Integer getMediatypeid_() {
        return mediatypeid_;
    }
	
    public void setMediatypeid_ (Integer mediatypeid) {
        this.mediatypeid_ =  mediatypeid;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @invoicelineTrackViaTrackid-getter-track@
    public Set<Invoiceline> getInvoicelineTrackViaTrackid() {
        if (invoicelineTrackViaTrackid == null){
            invoicelineTrackViaTrackid = new HashSet<Invoiceline>();
        }
        return invoicelineTrackViaTrackid;
    }

    public void setInvoicelineTrackViaTrackid (Set<Invoiceline> invoicelineTrackViaTrackid) {
        this.invoicelineTrackViaTrackid = invoicelineTrackViaTrackid;
    }	
    
    public void addInvoicelineTrackViaTrackid (Invoiceline element) {
    	    getInvoicelineTrackViaTrackid().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @playlisttrackViaTrackByTrackid-getter-track@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<Playlist> getPlaylistPlaylisttrackViaPlaylistid() {
        if (playlistPlaylisttrackViaPlaylistid == null){
            playlistPlaylisttrackViaPlaylistid = new HashSet<Playlist>();
        }
        return playlistPlaylisttrackViaPlaylistid;
    }

    public void setPlaylistPlaylisttrackViaPlaylistid (Set<Playlist> playlistPlaylisttrackViaPlaylistid) {
        this.playlistPlaylisttrackViaPlaylistid = playlistPlaylisttrackViaPlaylistid;
    }
    	
    public void addPlaylistPlaylisttrackViaPlaylistid (Playlist element) {
        getPlaylistPlaylisttrackViaPlaylistid().add(element);
    }
	


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}