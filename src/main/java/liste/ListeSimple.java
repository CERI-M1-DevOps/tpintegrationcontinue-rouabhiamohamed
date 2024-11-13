package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Returns the current size of the list.
     * @return the number of elements in the list
     */
    public long getSize() {
        return size;
    }

    /**
     * Adds a new element to the beginning of the list.
     * @param element the element to be added
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifies the first occurrence of a specified element with a new value.
     * @param element the element to replace
     * @param nouvelleValeur the new value to set
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && !courant.getElement().equals(element)) {
            courant = courant.getSuivant();
        }
        if (courant != null) {
            courant.setElement(nouvelleValeur);
        }
    }

    /**
     * Modifies all occurrences of a specified element with a new value.
     * @param element the element to replace
     * @param nouvelleValeur the new value to set for all occurrences
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element) {
                courant.setElement(nouvelleValeur);
            }
            courant = courant.getSuivant();
        }
    }

    /**
     * Returns a string representation of the list.
     * @return a string displaying each node's element in the list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     * @param element the element to remove
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Removes all occurrences of the specified element from the list.
     * @param element the element to remove
     */
    public void supprimeTous(int element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Recursively removes all occurrences of the specified element from the list.
     * @param element the element to remove
     * @param tete the current node being checked
     * @return the next node in the list after removal
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else {
            return null;
        }
    }

    /**
     * Retrieves the node before the last node in the list.
     * @return the second-to-last node, or null if the list has fewer than two nodes
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null) {
            return null;
        } else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Reverses the order of elements in the list.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Finds the node that precedes a given node.
     * @param r the node whose predecessor is to be found
     * @return the node that precedes the given node
     */
    public Noeud getPrecedent(Noeud r) {
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Swaps two nodes in the list.
     * @param r1 the first node to swap
     * @param r2 the second node to swap
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2) {
            return;
        }
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }
}
