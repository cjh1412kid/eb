U S E   [ Y H S m a r t D a t a R e t a i l ]  
 G O  
  
 / * * * * * *   O b j e c t :     T r i g g e r   [ d b o ] . [ s p l i t S a l e S h o e s D e t a i l ]         S c r i p t   D a t e :   2 0 1 8 / 1 2 / 1 0   1 5 : 1 5 : 4 7   * * * * * * /  
 S E T   A N S I _ N U L L S   O N  
 G O  
  
 S E T   Q U O T E D _ I D E N T I F I E R   O N  
 G O  
  
  
 - -   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  
 - -   A u t h o r : 	 	 < A u t h o r , , N a m e >  
 - -   C r e a t e   d a t e :   < C r e a t e   D a t e , , >  
 - -   D e s c r i p t i o n : 	 < D e s c r i p t i o n , , >  
 - -   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  
 C R E A T E   T R I G G E R   [ d b o ] . [ s p l i t S a l e S h o e s D e t a i l ]    
       O N     [ d b o ] . [ N W G o o d s _ S a l e S h o e s D e t a i l ]      
       A F T E R   I N S E R T  
 A S    
 B E G I N  
 	 - -   S E T   N O C O U N T   O N   a d d e d   t o   p r e v e n t   e x t r a   r e s u l t   s e t s   f r o m  
 	 - -   i n t e r f e r i n g   w i t h   S E L E C T   s t a t e m e n t s .  
 	 S E T   N O C O U N T   O N ;  
  
         - -   I n s e r t   s t a t e m e n t s   f o r   t r i g g e r   h e r e  
  
 	 D E C L A R E   @ s e q   I N T ,   @ p e r i o d S e q   I N T ;   - - �^�S0�l!k�^�S 
 	 D E C L A R E   @ t a b l e N a m e   v a r c h a r ( 5 0 ) ;     - - h�T 
  
 	 - -   �[IN8nh.   �penc:N�eh�-N@b	g�f�e�e��'Y�N�eh� g'Y�f�e�e���vpenc�vL i s t 	� 
 	 D E C L A R E   t e s t _ c u r s o r   C U R S O R   F A S T _ F O R W A R D   F O R    
 	 	 S E L E C T   S e q ,   P e r i o d S e q   F R O M   i n s e r t e d ;  
  
 	 - -   Sb _8nh.  
 	 O P E N   t e s t _ c u r s o r ;  
  
 	 	 W H I L E   1 = 1  
 	 	 B E G I N  
 	 	 	 - -   kXEQpenc.  
 	 	 	 F E T C H   N E X T   F R O M   t e s t _ c u r s o r   I N T O   @ s e q ,   @ p e r i o d S e q ;  
 	 	 	 - -   GP�Y*g�h"}0Rpenc� ��Q�_�s.  
 	 	 	 I F   @ @ f e t c h _ s t a t u s   ! =   0   B R E A K ;  
  
 	 	 	 - - $R�e���ceQ�T _h� 
 	 	 	 s e t   @ t a b l e N a m e   =   ' [ d b o ] . [ N W G o o d s _ S a l e S h o e s D e t a i l _ '   +   c o n v e r t ( v a r c h a r ,   @ p e r i o d S e q )   +   ' ] ' ;  
  
 	 	 	 - -   \�e�ceQ�vpenc�ceQ�[�^Rh� 
 	 	 	 s e l e c t   *   i n t o   # t e m p   f r o m   i n s e r t e d   w h e r e   S e q   =   @ s e q  
 	 	 	 e x e c ( ' I N S E R T   I N T O   '   +   @ t a b l e N a m e   +   '   S E L E C T   *   F R O M   # t e m p ' )  
 	 	 	 d r o p   t a b l e   # t e m p  
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
 E N D  
 G O  
  
 A L T E R   T A B L E   [ d b o ] . [ N W G o o d s _ S a l e S h o e s D e t a i l ]   E N A B L E   T R I G G E R   [ s p l i t S a l e S h o e s D e t a i l ]  
 G O  
  
  
 