using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Coordination.Model
{
    public class User : DatabaseObject
    {
        public  string Username
        {
            get; set;
        }
        public  string Password
        {
            get; set;
        }
        public  string Email
        {
            get; set;
        }
        public  string Latitude
        {
            get; set;
        }
        public  string Longitude
        {
            get; set;
        }
        public virtual ICollection<JobAssignment> JobAssignments
        {
            get; set;
        }
    }
}
