U S E   [ Y H S m a r t D a t a R e t a i l ]  
 G O  
  
 / * * * * * *   O b j e c t :     T r i g g e r   [ d b o ] . [ c r e a t e T r y A n d S a l e S u b T a b l e ]         S c r i p t   D a t e :   2 0 1 8 / 1 2 / 1 0   1 5 : 1 4 : 4 5   * * * * * * /  
 S E T   A N S I _ N U L L S   O N  
 G O  
  
 S E T   Q U O T E D _ I D E N T I F I E R   O N  
 G O  
  
 - -   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  
 - -   A u t h o r : 	 	 < A u t h o r , , N a m e >  
 - -   C r e a t e   d a t e :   < C r e a t e   D a t e , , >  
 - -   D e s c r i p t i o n : 	 < D e s c r i p t i o n , , >  
 - -   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  
 C R E A T E   T R I G G E R   [ d b o ] . [ c r e a t e T r y A n d S a l e S u b T a b l e ]    
       O N     [ d b o ] . [ N W G o o d s _ P e r i o d ]      
       A F T E R   I N S E R T  
 A S    
 B E G I N  
 	 - -   S E T   N O C O U N T   O N   a d d e d   t o   p r e v e n t   e x t r a   r e s u l t   s e t s   f r o m  
 	 - -   i n t e r f e r i n g   w i t h   S E L E C T   s t a t e m e n t s .  
 	 S E T   N O C O U N T   O N ;  
  
         - -   I n s e r t   s t a t e m e n t s   f o r   t r i g g e r   h e r e  
  
 	 D E C L A R E   @ p e r i o d S e q   I N T ;     - - �l!k 
 	 D E C L A R E   @ t r y V a l i d T a b l e N a m e   v a r c h a r ( 5 0 ) ;     - - h�T 
 	 D E C L A R E   @ t r y I n v a l i d T a b l e N a m e   v a r c h a r ( 5 0 ) ;     - - h�T 
 	 D E C L A R E   @ s a l e T a b l e N a m e   v a r c h a r ( 5 0 ) ;     - - h�T 
  
 	 - -   �[IN8nh.   �penc:N�eh�-N@b	g�f�e�e��'Y�N�eh� g'Y�f�e�e���vpenc�vL i s t 	� 
 	 D E C L A R E   t e s t _ c u r s o r   C U R S O R   F A S T _ F O R W A R D   F O R    
 	 	 S E L E C T   S e q   F R O M   i n s e r t e d ;  
  
 	 - -   Sb _8nh.  
 	 O P E N   t e s t _ c u r s o r ;  
  
 	 	 W H I L E   1 = 1  
 	 	 B E G I N  
 	 	 	 - -   kXEQpenc.  
 	 	 	 F E T C H   N E X T   F R O M   t e s t _ c u r s o r   I N T O   @ p e r i o d S e q ;  
 	 	 	 - -   GP�Y*g�h"}0Rpenc� ��Q�_�s.  
 	 	 	 I F   @ @ f e t c h _ s t a t u s   ! =   0   B R E A K ;  
  
 	 	 	 - - h�T 
 	 	 	 s e t   @ t r y V a l i d T a b l e N a m e   =   ' [ d b o ] . [ N W G o o d s _ T r y S h o e s D e t a i l _ V a l i d _ '   +   c o n v e r t ( v a r c h a r ,   @ p e r i o d S e q )   +   ' ] ' ;     - - 	gHeՋz 
 	 	 	 s e t   @ t r y I n v a l i d T a b l e N a m e   =   ' [ d b o ] . [ N W G o o d s _ T r y S h o e s D e t a i l _ I n v a l i d _ '   +   c o n v e r t ( v a r c h a r ,   @ p e r i o d S e q )   +   ' ] ' ;       - - �eHeՋz 
 	 	 	 s e t   @ s a l e T a b l e N a m e   =   ' [ d b o ] . [ N W G o o d s _ S a l e S h o e s D e t a i l _ '   +   c o n v e r t ( v a r c h a r ,   @ p e r i o d S e q )   +   ' ] ' ;     - -  �.Uh� 
  
 	 	 	 - -   R�^�[�^Rh� 
 	 	 	 e x e c ( '  
 	 	 	 C R E A T E   T A B L E   ' +   @ t r y V a l i d T a b l e N a m e   +   ' (  
 	 	 	 [ S e q ]   [ i n t ]   N O T   N U L L ,  
 	 	 	 [ S h o e S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ S h o e I D ]   [ v a r c h a r ] ( 5 0 )   N U L L ,  
 	 	 	 [ T r y C o u n t ]   [ i n t ]   N U L L ,  
 	 	 	 [ T r y T i m e s ]   [ i n t ]   N U L L ,  
 	 	 	 [ S h o p S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ D a t a T i m e ]   [ d a t e t i m e ]   N U L L ,  
 	 	 	 [ I n p u t T i m e ]   [ d a t e t i m e ]   N U L L ,  
 	 	 	 [ D e l ]   [ i n t ]   N O T   N U L L ,  
 	 	 	   P R I M A R Y   K E Y   C L U S T E R E D    
 	 	 	 (  
 	 	 	 	 [ S e q ]   A S C  
 	 	 	 ) W I T H   ( P A D _ I N D E X   =   O F F ,   S T A T I S T I C S _ N O R E C O M P U T E   =   O F F ,   I G N O R E _ D U P _ K E Y   =   O F F ,   A L L O W _ R O W _ L O C K S   =   O N ,   A L L O W _ P A G E _ L O C K S   =   O N )   O N   [ P R I M A R Y ]  
 	 	 	 )   O N   [ P R I M A R Y ]  
 	 	 	 ' )  
  
 	 	 	 e x e c ( '  
 	 	 	 C R E A T E   T A B L E   ' +   @ t r y I n v a l i d T a b l e N a m e   +   ' (  
 	 	 	 [ S e q ]   [ i n t ]   N O T   N U L L ,  
 	 	 	 [ S h o e S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ S h o e I D ]   [ v a r c h a r ] ( 5 0 )   N U L L ,  
 	 	 	 [ T r y C o u n t ]   [ i n t ]   N U L L ,  
 	 	 	 [ T r y T i m e s ]   [ i n t ]   N U L L ,  
 	 	 	 [ S h o p S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ D a t a T i m e ]   [ d a t e t i m e ]   N U L L ,  
 	 	 	 [ I n p u t T i m e ]   [ d a t e t i m e ]   N U L L ,  
 	 	 	 [ D e l ]   [ i n t ]   N O T   N U L L ,  
 	 	 	   P R I M A R Y   K E Y   C L U S T E R E D    
 	 	 	 (  
 	 	 	 	 [ S e q ]   A S C  
 	 	 	 ) W I T H   ( P A D _ I N D E X   =   O F F ,   S T A T I S T I C S _ N O R E C O M P U T E   =   O F F ,   I G N O R E _ D U P _ K E Y   =   O F F ,   A L L O W _ R O W _ L O C K S   =   O N ,   A L L O W _ P A G E _ L O C K S   =   O N )   O N   [ P R I M A R Y ]  
 	 	 	 )   O N   [ P R I M A R Y ]  
 	 	 	 ' )  
  
  
 	 	 	 e x e c ( '  
 	 	 	 C R E A T E   T A B L E   ' +   @ s a l e T a b l e N a m e   +   ' (  
 	 	 	 [ S e q ]   [ i n t ]   N O T   N U L L ,  
 	 	 	 [ P e r i o d S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ A r e a S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ B r a n c h O f f i c e S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ S h o p S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ S a l e D a t e ]   [ d a t e t i m e ]   N U L L ,  
 	 	 	 [ S h o e S e q ]   [ i n t ]   N U L L ,  
 	 	 	 [ S h o e I D ]   [ v a r c h a r ] ( 5 0 )   N U L L ,  
 	 	 	 [ O r d e r C o u n t ]   [ i n t ]   N U L L ,  
 	 	 	 [ S a l e C o u n t ]   [ i n t ]   N U L L ,  
 	 	 	 [ C o s t ]   [ d e c i m a l ] ( 1 0 ,   2 )   N U L L ,  
 	 	 	 [ T a g P r i c e ]   [ d e c i m a l ] ( 1 0 ,   2 )   N U L L ,  
 	 	 	 [ R e a l P r i c e ]   [ d e c i m a l ] ( 1 0 ,   2 )   N U L L ,  
 	 	 	 [ I n p u t T i m e ]   [ d a t e t i m e ]   N U L L ,  
 	 	 	 [ D e l ]   [ i n t ]   N O T   N U L L ,  
 	 	 	   P R I M A R Y   K E Y   C L U S T E R E D    
 	 	 	 (  
 	 	 	 	 [ S e q ]   A S C  
 	 	 	 ) W I T H   ( P A D _ I N D E X   =   O F F ,   S T A T I S T I C S _ N O R E C O M P U T E   =   O F F ,   I G N O R E _ D U P _ K E Y   =   O F F ,   A L L O W _ R O W _ L O C K S   =   O N ,   A L L O W _ P A G E _ L O C K S   =   O N )   O N   [ P R I M A R Y ]  
 	 	 	 )   O N   [ P R I M A R Y ]  
 	 	 	 ' )  
  
  
 	 	 E N D ;  
  
 	 - -   sQ�8nh 
 	 C L O S E   t e s t _ c u r s o r ;  
  
 	 - -   ʑ>e8nh.  
 	 D E A L L O C A T E   t e s t _ c u r s o r ;  
  
  
  
 E N D  
 G O  
  
 A L T E R   T A B L E   [ d b o ] . [ N W G o o d s _ P e r i o d ]   E N A B L E   T R I G G E R   [ c r e a t e T r y A n d S a l e S u b T a b l e ]  
 G O  
  
  
 