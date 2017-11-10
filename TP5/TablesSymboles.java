import java.util.*;

/** 
 * Tables des symboles :
 *     _ Une table pour les variables globales; et,
 *     _ une pour les variables locales.
 *
 *     Chaque table donne pour chaque variable sa position, donc son adresse dans la pile.
 *     On recherche d'abord en local, si défini. Comme on manipule des variables typées, on stocke également le type.
 *     On utlise ici des tables de hachage stockant des objets AdresseType.
 *
 * Pour autoriser un fonction et une variable de même nom, on ajoute aussi :
 *     _ Une Table des étiquettes des fonctions.
 *
 * Note : une pile de tables pourrait être nécessaire,
 *        si on voulait pouvoir définir des fonctions dans des fonctions...
 */
class TablesSymboles {
    private TableSymboles _tableGlobale = new TableSymboles();
    private TableSymboles _tableLocale = null;
    private Stack<TableSymboles> _tablesLocales = new Stack<TableSymboles>();
    
    private int n = 0;

    public void newTableLocale() { 
	   _tableLocale = new TableSymboles();
       _tablesLocales.push(new TableSymboles());
    }
    public void dropTableLocale() { 
	   _tableLocale = null; 
       if(!_tablesLocales.empty()) {
            _tablesLocales.pop();
       }
    }
    public void putVar(String s, String t) { 
	   if (!_tablesLocales.empty()) {
	       _tablesLocales.peek().putVar(s,t);
        } else {
	       _tableGlobale.putVar(s,t);
           n+=AdresseType.getSize(t); 
        }
    }

    public Integer getTaille() { return n;}

    public Integer getLocaleTaille() {
        return !_tablesLocales.empty() ? _tablesLocales.peek().getTaille() : 0 ;
    }
    /**
     * Les adresses des variables locales sont comptées négativement à partir de fp.
     * Vision de la pile:
     *                                fp
     *                                \/
     *     ...  rr  p1  p2  ... pcr  fp(-1)
     * 
     * où rr est la place pour la valeur retournée par la fonction (éventuellement)
     * et :
     *     p1 est la place du 1er paramètre 
     *     p2 est la place du 2e paramètre
     *     ...
     *     pcr est le compteur de programme (pc) au retour 
     *                (là où il faut se brancher après sortie de la fonction)  
     *     fp(-1) est la valeur du fp précédent (nécessaire pour restaurer l'environnement)  
     *     fp est la valeur du fp courant, là où est stocké fp(-1)
     * 
     * La distance dans la pile séparant la place de la variable locale correspondant
     * au premier paramètre  de la place pointée par le fp courant est donc égale : 
     * au nombre de paramètres de la fonction plus 2 (à cause de pc et fp qui sont empilés par CALL).
     * 
     * L'adresse par rapport à fp d'une variable est toujours négative et se calcule comme :
     * son rang, moins le nombre de paramètres, moins 2.
     *
     * Note : on a triché ci-dessus dans l'explication pour faire comme si on avait uniquement des variables de type int qui ne prenne qu'une cellule de la pile.
     * Pour gérer les flottants (qui sont supportés "nativement" par la MVàP), il faut tenir compte des tailles des variables qui compte pour 1 ou pour 2 dans 
     * le calcul 
     *
     */
    private AdresseType getAdresseTypeLocale(String symbol) {
    	if (!_tablesLocales.empty()) {
            AdresseType a = null;
            for(TableSymboles ts : _tablesLocales) {
                a = ts.getAdresseType(symbol);
                if (a != null) { // on a trouvé
                 return new AdresseType(a.adresse - (ts.getTaille() + 2), a.type);
                }
            }
    	    
    	}

    	return null;
    }
   
    private AdresseType getAdresseTypeGlobale(String symbol) {
	return _tableGlobale.getAdresseType(symbol);
    }
   
    
    /** donne l'adresse de la variable locale à une fonction (négative) ou globale (positive) */
    public AdresseType getAdresseType(String symbol) { 
	AdresseType a = getAdresseTypeLocale(symbol);
	if (a == null)
	    a = getAdresseTypeGlobale(symbol);
	if (a != null)                    
	    return a;
            
	return null; // n'importe quoi, et l'adresse n’existe pas!
    }

    private HashMap<String, AdresseType> _tableFonctions = new HashMap<String, AdresseType>();    
    
    public AdresseType getFonction(String fonction) {
	AdresseType l = _tableFonctions.get(fonction);
	if (l != null)
	    return l;
	return null;
    }

    public boolean nouvelleFonction(String fonction, int label, String t) {
        AdresseType fat = _tableFonctions.get(fonction);
	if ( fat!= null ) {
	    return false;
	}
	_tableFonctions.put(fonction, new AdresseType(label,t));
	return true;
    }
}
    
