package com.iastate.edu;

import library.Node;

public class ACV3<K,V> extends Node<K,V>{
	ACV3<K,V> p,l,r,pr;
	
	ACV3(int h, K k, V v, Node<K, V> n) {
        super(h, k, v, n);
    }
	
	final ACV3<K, V> r() {
        for (ACV3<K, V> r = this, p; ; ) {
            if ((p = r.p) == null)
                return r;
            r = p;
        }
    }

	final ACV3<K,V> put(Node<K,V> tab, int h, K k, V v) {
		Object kc = null;
		boolean s = false;
		ACV3<K,V> rt = (p != null) ? r() : this;
		for (ACV3<K,V> p = rt; ; ) {
            int dir, ph;
            K pk;
			if ((ph = p.hash) > h)
                dir = -1;
            else if (ph < h)
                dir = 1;
            else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
                return p;
            else if ((kc == null && (kc = c(k)) == null) || (dir = compareComparables(kc, k, pk)) == 0) {
                if (!s) {
                    ACV3<K,V> q, ch;
                    s = true;
                    if (((ch = p.l) != null && (q = ch.f(h, k, kc)) != null) || ((ch = p.r) != null && (q = ch.f(h, k, kc)) != null))
                        return q;
                }
                dir = t(k, pk);
            }
            ACV3<K,V> xp = p;
            if ((p = (dir <= 0) ? p.l : p.r) == null) {
                Node<K,V> xpn = xp.next;
                ACV3<K,V> x = new ACV3(h, k, v, xpn);
                if (dir <= 0)
                    xp.l = x;
                else
                    xp.r = x;
                xp.next = x;
                x.p = x.pr = xp;
                if (xpn != null)
                    ((ACV3<K,V>) xpn).pr = x;
                // call to guard against ACV should have been here
                return null;
            }
        }
	}
	
	private ACV3<K, V> f(int h, K k, Object kc) {
		// stub
		return null;
	}

	private int compareComparables(Object kc, K k, K pk) {
		// stub
		return 0;
	}

	private Object c(K k) {
		// stub
		return null;
	}

	private int t(K k, K pk) {
		// stub
		return 0;
	}

}
