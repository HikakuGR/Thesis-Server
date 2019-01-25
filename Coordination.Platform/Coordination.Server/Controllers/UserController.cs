using Coordination.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace Coordination.Server.Controllers
{
    public class UserController : BaseController
    {
       
        //
        // GET: /Default1/

        public ActionResult Index()
        {
            return View(db.Users.ToList());
        }

        [HttpGet]
        public ActionResult Create(int? id)
        {
            return View();
        }

        // POST: /Movies/Create
        [HttpPost]
        public ActionResult Create()
        {
            string username = Request["Username"];
            string password = Request["Password"];
            string email = Request["Email"];
            User existingUser = db.Users.FirstOrDefault(user => user.Username == username);
            if (existingUser==null)
            {


                User user = new User();
                user.Username = username;
                user.Password = password;
                user.Email = email;
                
                db.Users.Add(user);
                db.SaveChanges();
            }
            else
            {
                ViewData["ErrorMessage"] = "User already exists";
            }
            return View("Index", db.Users.ToList());
        }

        // GET: /Movies/Edit/5
        [HttpGet]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            User user = db.Users.FirstOrDefault(usr => usr.ID == id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View(user);
        }

        // POST: /Movies/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        public ActionResult Edit()
        {
            string username = Request["Username"];
            string password = Request["Password"];
            string email = Request["Email"];
            int? id = int.Parse(Request["id"]);

            User user = db.Users.FirstOrDefault(usr => usr.ID == id);
            if (user == null)
            {
                return HttpNotFound();
            }
            user.Username = username;
            user.Password = password;
            user.Email = email;

            //db.Users.Add(user);
            db.Entry(user).State = System.Data.Entity.EntityState.Modified;
            db.SaveChanges();
            return View("Index", db.Users.ToList());
        }

        public ActionResult Delete(int? id)
        {
            try { 
            User user = db.Users.FirstOrDefault(x => x.ID == id);
            if (user == null)
            {
                return HttpNotFound();
            }
            db.Users.Remove(user);
            db.SaveChanges();
                return View("Index", db.Users.ToList());
            }
             catch (Exception ex)
            {
                ViewBag.ErrorMessage = "You cannot delete a user assigned to a job or connected to a completed job!";
                return View("Index", db.Users.ToList());
            }
        }

        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            Model.User user = db.Users.FirstOrDefault(x => x.ID == id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View(user);

        }

    }
}
