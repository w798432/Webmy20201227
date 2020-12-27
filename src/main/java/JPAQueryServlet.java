
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Supplier;

/**
 * Servlet implementation class JPASupplierServlet
 */
@WebServlet("/JPAQueryServlet")
public class JPAQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JPAQueryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("webmv20201227");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		String jpql = "select e from Supplier e where e.supId >= :arg1 AND e.supName=:arg2";
		Query query = entityManager.createQuery(jpql, Supplier.class);
		query.setParameter("arg1", 100);
		query.setParameter("arg2", "The High Ground");
		
		List<Supplier> result = query.getResultList();
		request.setAttribute("supplier", result);
		request.getRequestDispatcher("viewSupplier.jsp").forward(request, response);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
