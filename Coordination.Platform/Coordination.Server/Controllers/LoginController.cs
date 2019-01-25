using Coordination.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Coordination.Server.Controllers
{
    public class LoginController : BaseController
    {
        //
        // GET: /Login/

        public ActionResult Index()
        {
            return View();
        }
        public ActionResult LogOut()
        {
            //Session.Abandon();
            Session.Clear();
            return View("../Login/Index");
        }

        public ActionResult Validate()
        {
            string username = Request["username"];
            string password = Request["password"];


            var foundUsers = db.Users.Where(user => user.Username == username && user.Password == password);

            if (foundUsers.Count() == 1)
            {
                Session["currentUser"] = username;

            }
            else
            {
                ViewBag.ErrorMessage="Wrong Credentials!";
                return View("Index");
            }
            return Redirect("~/Home/Index");

        }
    }
}
